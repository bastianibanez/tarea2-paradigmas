package app.interfaces;

public interface GestorArchivos {
    void CrearArchivo(String ruta, Prueba prueba);

    Prueba LeerArchivo(String ruta);

    void ActualizarArchivo(String ruta, Prueba prueba);

    void BorrarArchivo(String ruta);
}
