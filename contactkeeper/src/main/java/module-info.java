module cKmodule {
	requires javafx.controls;
	requires javafx.graphics;
	requires javafx.fxml;
	requires javafx.base;
	requires java.persistence;
	requires java.management;
	requires java.xml;
	requires jdk.jfr;

	opens model to eclipselink;
	opens controller to javafx.fxml;
	opens application to javafx.graphics, javafx.fxml;
}