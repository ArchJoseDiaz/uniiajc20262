import java.util.ArrayList;
import java.util.List;

// 1. Clase auxiliar (Sin la palabra public)
class Producto {
    private String codigo;
    private String nombre;
    private double precio;

    public Producto(String codigo, String nombre, double precio) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
    }

    public String getCodigo() { return codigo; }
    public String getNombre() { return nombre; }
    public double getPrecio() { return precio; }
    public void setPrecio(double precio) { this.precio = precio; }

    @Override
    public String toString() {
        return "Producto[" + codigo + " | " + nombre + " | $" + precio + "]";
    }
}

// 2. Clase principal (Lleva el mismo nombre del archivo)
public class GestionProductos {
    public static void main(String[] args) {
        // 1. Declarar e instanciar la lista de objetos Producto
        List<Producto> inventario = new ArrayList<>();

        // 2. Agregar productos a la lista (add)
        inventario.add(new Producto("P01", "Teclado Mecanico", 150000.0));
        inventario.add(new Producto("P02", "Mouse Inalambrico", 80000.0));
        inventario.add(new Producto("P03", "Monitor 24 pulgadas", 650000.0));
        inventario.add(new Producto("P04", "Diadema Gamer", 120000.0));

        // 3. Listar todos los productos mediante bucle for-each
        System.out.println("--- INVENTARIO INICIAL ---");
        for (Producto p : inventario) {
            System.out.println(p);
        }

        // 4. Buscar un producto por su codigo
        String codigoBuscado = "P02";
        Producto encontrado = null;
        for (Producto p : inventario) {
            if (p.getCodigo().equalsIgnoreCase(codigoBuscado)) {
                encontrado = p;
                break;
            }
        }

        if (encontrado != null) {
            System.out.println("\nProducto encontrado: " + encontrado);
            // Actualizar el precio usando el setter del objeto
            encontrado.setPrecio(85000.0);
            System.out.println("Precio actualizado: " + encontrado);
        } else {
            System.out.println("\nProducto con codigo " + codigoBuscado + " no encontrado.");
        }

        // 5. Eliminar un producto de la lista por su indice
        String codigoEliminar = "P04";
        for (int i = 0; i < inventario.size(); i++) {
            if (inventario.get(i).getCodigo().equalsIgnoreCase(codigoEliminar)) {
                Producto eliminado = inventario.remove(i);
                System.out.println("\nProducto eliminado del inventario: " + eliminado.getNombre());
                break;
            }
        }

        // 6. Calcular el valor total del inventario acumulando los precios
        double valorTotal = 0;
        for (Producto p : inventario) {
            valorTotal += p.getPrecio();
        }

        // 7. Mostrar estado final del inventario
        System.out.println("\n--- INVENTARIO FINAL ---");
        for (Producto p : inventario) {
            System.out.println(p);
        }
        System.out.println("Total de productos en lista: " + inventario.size());
        System.out.println("Valor total del inventario: $" + valorTotal);
    }
}
