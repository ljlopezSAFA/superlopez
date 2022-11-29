package producto;


import com.app.superlopez.model.Producto;
import com.app.superlopez.repository.ProductoRepository;
import com.app.superlopez.utilidades.UtilidadesProducto;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.springframework.test.util.AssertionErrors.assertEquals;
import static org.springframework.test.util.AssertionErrors.assertNotNull;



@ExtendWith(SpringExtension.class)
@DataJpaTest
@TestPropertySource(properties = {
        "spring.jpa.hibernate.ddl-auto=validate",
        "spring.datasource.driver-class-name=org.h2.Driver",
        "spring.datasource.url=jdbc:h2:mem:db",
        "spring.jpa.properties.hibernate.default_schema=",
        "spring.jpa.hibernate.ddl-auto=update",
})
public class ProductoRepositoryTest {


    @Autowired
    ProductoRepository productoRepository;

    @Autowired
    TestEntityManager entityManager;



    @Test
    public void guardarProductoTest(){

        //Given
        Producto productoEsperado = UtilidadesProducto.crearProducto();

        //When
        Producto productoObtenido = productoRepository.save(productoEsperado);

        //Then
        assertNotNull( "No se ha guardado en bbdd el producto", productoObtenido);
        assertEquals("Los productos no coinciden ",productoEsperado.getDescripcion(),productoObtenido.getDescripcion());

    }


    @Test
    @DisplayName("ContactoRepository -> Test 1 -> Método buscar por id ")
    @Tag("Producto Repository")
    public void buscarProductoTest(){

        //Given
        Producto productoEsperado = UtilidadesProducto.crearProducto();
        entityManager.persist(productoEsperado);

        //When
        Producto productoObtenido = productoRepository.findById(productoEsperado.getId()).orElse(null);

        //Then
        assertNotNull( "No se ha encontrado producto", productoObtenido);
        assertEquals("Los productos no coinciden ",productoEsperado.getDescripcion(),productoObtenido.getDescripcion());

    }

}
