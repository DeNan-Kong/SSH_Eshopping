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
	private static final ThreadLocal<Session> threadLocal = new ThreadLocal<Session>();//sessionFactory���õ����̳߳ؼ���
    private static org.hibernate.SessionFactory sessionFactory;//sessionFactory������session�Ĺ���
	
    private static Configuration configuration = new Configuration();
    private static ServiceRegistry serviceRegistry; 

	static { //�����ʱ��ʼ��sessionFactory 
    	try {
			configuration.configure();
			serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
			sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		} catch (Exception e) {
			System.err.println("%%%% Error Creating SessionFactory %%%%");
			e.printStackTrace();
		}
    }
    private HibernateSessionFactory() {//˽�й��췽����ֹnew�����󣬱�֤sessionFactory�ĵ���  
    }
	
	/**
     * Returns the ThreadLocal Session instance.  Lazy initialize
     * the <code>SessionFactory</code> if needed.
     *
     *  @return Session
     *  @throws HibernateException
     */
    public static Session getSession() throws HibernateException {
        Session session = (Session) threadLocal.get();//���̳߳�����session

		if (session == null || !session.isOpen()) {//����̳߳��ǿյģ�����session��ʧ��  
			if (sessionFactory == null) {//���sessionFactory�ǿյģ��Ǿ��ٴ���һ�Σ���static����һ����
				rebuildSessionFactory();
			}
			session = (sessionFactory != null) ? sessionFactory.openSession()//sessionFactory��Ϊ�վʹ���һ��session 
					: null;
			threadLocal.set(session);//Ȼ������session�ŵ��̳߳��У��´�����
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
	public static org.hibernate.SessionFactory getSessionFactory() {//�ṩһ�������ӿ���������������sessionFactory 
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