package com.shop.utils;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

/**
 * Configures and provides access to Hibernate sessions, tied to the
 * current thread of execution.  Follows the Thread Local Session
 * pattern, see {@link http://hibernate.org/42.html }.
 */
public class HibernateSessionFactory {

    /** 
     * Location of hibernate.cfg.xml file.
     * Location should be on the classpath as Hibernate uses  
     * #resourceAsStream style lookup for its configuration file. 
     * The default classpath location of the hibernate config file is 
     * in the default package. Use #setConfigFile() to update 
     * the location of the configuration file for the current session.   
     */
	private static final ThreadLocal<Session> threadLocal = new ThreadLocal<Session>();//sessionFactory中用的是线程池技术
    private static org.hibernate.SessionFactory sessionFactory;//sessionFactory：创建session的工厂
	
    private static Configuration configuration = new Configuration();
    private static ServiceRegistry serviceRegistry; 

	static { //类加载时初始化sessionFactory 
    	try {
			configuration.configure();
			serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
			sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		} catch (Exception e) {
			System.err.println("%%%% Error Creating SessionFactory %%%%");
			e.printStackTrace();
		}
    }
    private HibernateSessionFactory() {//私有构造方法阻止new出对象，保证sessionFactory的单例  
    }
	
	/**
     * Returns the ThreadLocal Session instance.  Lazy initialize
     * the <code>SessionFactory</code> if needed.
     *
     *  @return Session
     *  @throws HibernateException
     */
    public static Session getSession() throws HibernateException {
        Session session = (Session) threadLocal.get();//从线程池中拿session

		if (session == null || !session.isOpen()) {//如果线程池是空的，或者session打开失败  
			if (sessionFactory == null) {//如果sessionFactory是空的，那就再创建一次，和static部分一样的
				rebuildSessionFactory();
			}
			session = (sessionFactory != null) ? sessionFactory.openSession()//sessionFactory不为空就创建一个session 
					: null;
			threadLocal.set(session);//然后把这个session放到线程池中，下次再拿
		}

        return session;
    }

	/**
     *  Rebuild hibernate session factory
     *
     */
	public static void rebuildSessionFactory() {
		try {
			configuration.configure();
			serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
			sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		} catch (Exception e) {
			System.err.println("%%%% Error Creating SessionFactory %%%%");
			e.printStackTrace();
		}
	}

	/**
     *  Close the single hibernate session instance.
     *
     *  @throws HibernateException
     */
    public static void closeSession() throws HibernateException {
        Session session = (Session) threadLocal.get();
        threadLocal.set(null);

        if (session != null) {
            session.close();
        }
    }

	/**
     *  return session factory
     *
     */
	public static org.hibernate.SessionFactory getSessionFactory() {//提供一个公共接口让外界获得这个单例sessionFactory 
		return sessionFactory;
	}
	/**
     *  return hibernate configuration
     *
     */
	public static Configuration getConfiguration() {
		return configuration;
	}

}