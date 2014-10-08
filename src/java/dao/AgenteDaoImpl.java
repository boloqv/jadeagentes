
package dao;

import java.util.List;
import model.Agentes;
import model.Usuario;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import util.HibernateUtil;


public class AgenteDaoImpl implements AgenteDao{

        @Override
        public Agentes findByUsuario(Agentes agente) {
        Agentes model1= new Agentes();
         Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        
        try {
            sesion.beginTransaction();
            String sql = "FROM Agentes where usuNombre='"+agente.getAgeCodigo()+"'";
            model1 =  (Agentes) sesion.createQuery(sql).uniqueResult();
//            sesion.beginTransaction().commit();
            sesion.close();
        } catch (HibernateException e) {
            System.out.print("maldito"+e);            
//            sesion.beginTransaction().rollback();
        }
        return model1;
    }

    @Override
    public List<Agentes> findAll() {
        List<Agentes> listado = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "FROM Agentes";
        try {
            sesion.beginTransaction();
            listado = sesion.createQuery(sql).list();
            
//            sesion.beginTransaction().commit();
            sesion.close();
        } catch (HibernateException e) {
            System.out.print("maldito"+e);            
//            sesion.beginTransaction().rollback();
        }
        return listado;
    }

    public Agentes findByAgentes(Agentes agente) {    
        return agente;
    }

    @Override
    public Agentes login(Agentes agente) {
        return agente;
    }
    
    @Override
   public void guardarAgente(Agentes agente){
        Session sesion  = null;
        try {
            sesion = HibernateUtil.getSessionFactory().openSession();
            sesion.beginTransaction();
            sesion.save(agente);
            sesion.getTransaction().commit();
        }catch (HibernateException e){
            System.out.println(e.getMessage());
            
        }finally{
               if(sesion != null){
                   sesion.close();
               }
        }
    }

    @Override
    public void eliminarAgente(Agentes agente) {
        Session session = null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(agente);
            session.getTransaction().commit();
        }catch (HibernateException e){
            System.out.println(e.getMessage());
//            session.getTransaction().rollback();
        }finally {
            if(session!= null){
                session.close();
            }
        }
    }

    @Override
    public void editarAgente(Agentes agente) {
        Session session = null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(agente);
            session.getTransaction().commit();
        }catch (HibernateException e){
            System.out.println(e.getMessage());
            
            
        }finally {
            if (session != null){
                session.close ();
            }
        }
    }
    
}