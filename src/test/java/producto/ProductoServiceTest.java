package producto;

import com.app.superlopez.model.Producto;
import com.app.superlopez.repository.ProductoRepository;
import com.app.superlopez.service.ProductoService;
import com.app.superlopez.utilidades.UtilidadesProducto;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.util.AssertionErrors.*;

@RunWith(MockitoJUnitRunner.class)
public class ProductoServiceTest {

    @Mock
    ProductoRepository productoRepository;

    @InjectMocks
    ProductoService productoService;



    @Test
    @DisplayName("Producto Service -> Test buscar por id")
    public void buscarPorIdTest(){

        //Given
        Producto productoEsperado = UtilidadesProducto.crearProducto();
        Mockito.when(productoRepository.findById(any())).thenReturn(Optional.of(productoEsperado));

        //When
        Producto productoObtenido = productoService.buscarPorId(1);

        //Then
        assertEquals("Los productos no coinciden",productoEsperado,productoObtenido);

    }



}
