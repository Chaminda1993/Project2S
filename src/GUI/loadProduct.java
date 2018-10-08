package GUI;

public class loadProduct {
        private String name;
        private String id;

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public loadProduct(String id, String name) {
            this.id = id;
            this.name = name;
        }
}
