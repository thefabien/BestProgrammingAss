# Use an official Tomcat base image
FROM tomcat:9-jdk11-openjdk

# Set environment variables (optional)
ENV JAVA_OPTS="-Xms512m -Xmx1024m"

# Copy the WAR file into the Tomcat webapps directory
COPY target/BestPractice-1.0-SNAPSHOT.war /usr/local/tomcat/webapps/ROOT.war

# Expose port 8080 for the Tomcat server
EXPOSE 8080

# Start Tomcat server
CMD ["catalina.sh", "run"]
