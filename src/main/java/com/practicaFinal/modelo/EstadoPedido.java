package com.practicaFinal.modelo;



public enum EstadoPedido {
    NUEVO{
        @Override
        public String toString(){
            return "Nuevo pedido";
        }
    },
    EN_PREPARACION{
        @Override
        public String toString(){
            return "Preparandose";
        }
    },
    EN_CAMINO{
        @Override
        public String toString(){
            return "En camino";
        }
    },
    ESPERANDO_CLIENTE{
        @Override
        public String toString(){
            return "Esperando cliente";
        }
    },
    ENTREGADO{
        @Override
        public String toString(){
            return "Entregado";
        }
    },
}
