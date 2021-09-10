package com.practicaFinal.modelo;



public enum TipoRecogida {
    ENVIO{  

        
        @Override
        public String toString(){
            return "Envío";
        }
        
        @Override
        public String getLabel() {
            return "Envío del pedido a mi dirección";
        }
    },
    RECOGIDA{
        @Override
        public String toString(){
            return "Recogida";
        }
        
        @Override
        public String getLabel() {
            return "Recogida del pedido en el restaurante";
        }
    };
    
    public String getLabel() {
        return "";
    }
}
