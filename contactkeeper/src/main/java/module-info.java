module cKmodule {
	
	requires javafx.controls;
	requires javafx.graphics;
	requires javafx.fxml;
	requires javafx.base;
	requires java.persistence;
	requires java.management;
	requires java.xml;
	requires jdk.jfr;
	requires org.hibernate.orm.core;
	requires java.base;
	requires jbcrypt;
	requires java.desktop;

	opens controller to javafx.fxml;
	opens application to javafx.graphics, javafx.fxml;
}