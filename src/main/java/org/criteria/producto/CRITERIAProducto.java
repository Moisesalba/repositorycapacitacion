package org.criteria.producto;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.modelo.producto.Producto;

/*
 * Criteria: consultas ya predefinidas.
 * 
 */
public class CRITERIAProducto {

	public static void caseCriteriaProducto() {
		AnnotationConfiguration cfg = new AnnotationConfiguration();
		cfg.configure("hibernate.cfg.xml");
		SessionFactory factory = cfg.buildSessionFactory();
		Session session = factory.getCurrentSession();
		try {
			session.beginTransaction();
			Criteria cr = session.createCriteria(Producto.class);

			// Obtener los productos con el precio mayor 20.0
			// cr.add(Restrictions.gt("precio", 20.0));

			// Obtener los productos con el precio menor 20.0
			// cr.add(Restrictions.lt("precio", 20.0));

			// Filtrado de datos
			// cr.add(Restrictions.like("nombre", "L%"));

			// Obtener datos por rango
			// cr.add(Restrictions.between("precio", 20.0, 30.0));

			// Ordenamientos ascedentemente y descendentemente
			// cr.addOrder(Order.asc("id"));
			// cr.addOrder(Order.desc("id"));

			@SuppressWarnings("unchecked")
			List<Producto> productos = (List<Producto>) cr.list();

			for (Producto p : productos) {
				System.out.println(p.getId() + ", " + p.getNombre() + ", " + p.getPrecio());
			}
		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
		}
	}

	public static void caseCriteriaUniqueProducto() {
		AnnotationConfiguration cfg = new AnnotationConfiguration();
		cfg.configure("hibernate.cfg.xml");
		SessionFactory factory = cfg.buildSessionFactory();
		Session session = factory.getCurrentSession();
		try {
			session.beginTransaction();
			Criteria cr = session.createCriteria(Producto.class);
			// Obtener el numero de filas de la tabla.
			// cr.setProjection(Projections.rowCount("precio"));

			// Operaciones : promedio, min, max
			// cr.setProjection(Projections.avg("precio"));
			// cr.setProjection(Projections.min("precio"));
			// cr.setProjection(Projections.max("precio"));
			cr.setProjection(Projections.sum("precio"));

			Object resultad = cr.uniqueResult();
			System.out.println("Resultado = " + resultad);
			session.getTransaction().commit();
		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
		}
	}

	public static void main(String[] args) {
		// caseCriteriaProducto();
		caseCriteriaUniqueProducto();
	}

}
