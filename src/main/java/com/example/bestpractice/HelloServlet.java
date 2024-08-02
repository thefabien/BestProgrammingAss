package com.example.bestpractice;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import java.io.IOException;

@WebServlet("/myservlet")
public class HelloServlet extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(HelloServlet.class);
    private SessionFactory sessionFactory;

    @Override
    public void init() throws ServletException {
        try {
            Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
            sessionFactory = configuration.buildSessionFactory();
            logger.info("Hibernate SessionFactory initialized successfully.");
        } catch (Exception e) {
            logger.error("Error initializing Hibernate SessionFactory", e);
            throw new ServletException("Error initializing Hibernate", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        request.getRequestDispatcher("/input.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = null;
            try {
                User user = new User();
                user.setName(name);
                transaction = session.beginTransaction();
                session.save(user);
                transaction.commit();
                logger.info("User saved successfully: ID={}, Name={}", user.getId(), name);
                request.setAttribute("id", user.getId());
                request.setAttribute("name", name);
                request.getRequestDispatcher("/success.jsp").forward(request, response);
            } catch (Exception e) {
                if (transaction != null) {
                    transaction.rollback();
                }
                logger.error("Error saving user", e);
                request.setAttribute("error", "An error occurred: " + e.getMessage());
                request.getRequestDispatcher("/input.jsp").forward(request, response);
            }
        }
    }

    @Override
    public void destroy() {
        if (sessionFactory != null) {
            sessionFactory.close();
            logger.info("Hibernate SessionFactory closed.");
        }
    }
}