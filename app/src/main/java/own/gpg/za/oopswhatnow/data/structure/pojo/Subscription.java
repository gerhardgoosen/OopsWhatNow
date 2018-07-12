package own.gpg.za.oopswhatnow.data.structure.pojo;

/**
     * A Subscription item  
     */
    public  class Subscription {
        public final String id;
        public final String content;
        public final String details;
        public final String type;
        public final Double price;
        public boolean active = false;

        public Subscription(String id, String content, String details, Double price, String type,boolean active) {
            this.id = id;
            this.content = content;
            this.details = details;
            this.price = price;
            this.type = type;
            this.active = active;
        }

        @Override
        public String toString() {
            return content+ " R "+price ;
        }
    }