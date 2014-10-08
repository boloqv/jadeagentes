
package dao;

import java.util.List;
import model.Usuario;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import util.HibernateUtil;


public class UsuarioDaoImpl implements UsuarioDao{

    @Override
    public Usuario findByUsuario(Usuario usuario) {
        Usuario model1= new Usuario();
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        
        try {
            sesion.beginTransaction();
            String sql = "FROM Usuario where usuNombre='"+usuario.getUsuNombre()+"'";
            model1 =  (Usuario) sesion.createQuery(sql).uniqueResult();
//            sesion.beginTransaction().commit();
            sesion.close();
        } catch (HibernateException e) {
            System.out.print("maldito"+e);            
//            sesion.beginTransaction().rollback();
        }
        return model1;
    }

    @Override
    public Usuario login(Usuario usuario) {
        Usuario model = this.findByUsuario(usuario);
        if (model !=null){
            if(!usuario.getUsuClave().equals(model.getUsuClave())){
                model =null;
                
            }
        }
        return model;
    }

    @Override
    public List<Usuario> findAll() {
        List<Usuario> listado = null;
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "FROM Usuario";
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
    
   @Override
   public void guardar(Usuario usuario){
        Session sesion  = null;
        try {
            sesion = HibernateUtil.getSessionFactory().openSession();
            sesion.beginTransaction();
            sesion.save(usuario);
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
    public void eliminarusuario(Usuario usuario) {
        Session session = null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(usuario);
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
    public void editarusuario(Usuario usuario) {
        Session session = null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(usuario);
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
