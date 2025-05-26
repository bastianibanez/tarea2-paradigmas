package app.clases;

import java.util.List;
import java.util.ArrayList;

public class TaxonomiaBloom implements app.interfaces.TaxonomiaBloom {
    private final String[] nivelesTaxonomia = {
            "Crear",
            "Evaluar",
            "Analizar",
            "Aplicar",
            "Entender",
            "Recordar",
    };

    private String nivelTaxonomia;

    public TaxonomiaBloom(){
        nivelTaxonomia = "";
    }

    public TaxonomiaBloom(String nivelTaxonomia){
        this.nivelTaxonomia = nivelTaxonomia;
    }

    public void setNivel(String nivelTaxonomia) {
        for (String nivel: this.nivelesTaxonomia) {
            if (nivelTaxonomia.equalsIgnoreCase(nivel)) {
                this.nivelTaxonomia = nivel;
                return;
            }
        }
        System.out.println("Nivel taxonómico inválido. Las opciones son:");
        this.showNiveles();
    }

    public String getNivel(){
        return this.nivelTaxonomia;
    }

    public void showNivel(){
        System.out.println(this.nivelTaxonomia);
    }

    public void showNiveles(){
        for (int i = 0; i < this.nivelesTaxonomia.length; i++){
            System.out.println("[" + (i+1) + "] " + nivelesTaxonomia[i]);
        }
    }
}
