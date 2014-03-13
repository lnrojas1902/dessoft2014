
package co.edu.uniandes.csw.carritodecompras.logic.ejb;
import java.util.List;
import javax.inject.Inject;

import co.edu.uniandes.csw.carritodecompras.logic.dto.CarritoDeComprasDTO;
import co.edu.uniandes.csw.carritodecompras.logic.api._ICarritoDeComprasLogicService;
import co.edu.uniandes.csw.carritodecompras.persistence.api.ICarritoDeComprasPersistence;

public abstract class _CarritoDeComprasLogicService implements _ICarritoDeComprasLogicService {

	@Inject
	protected ICarritoDeComprasPersistence persistance;

	public CarritoDeComprasDTO createCarritoDeCompras(CarritoDeComprasDTO carritoDeCompras){
		return persistance.createCarritoDeCompras( carritoDeCompras); 
    }

	public List<CarritoDeComprasDTO> getCarritoDeComprass(){
		return persistance.getCarritoDeComprass(); 
	}

	public CarritoDeComprasDTO getCarritoDeCompras(Long id){
		return persistance.getCarritoDeCompras(id); 
	}

	public void deleteCarritoDeCompras(Long id){
	    persistance.deleteCarritoDeCompras(id); 
	}

	public void updateCarritoDeCompras(CarritoDeComprasDTO carritoDeCompras){
	    persistance.updateCarritoDeCompras(carritoDeCompras); 
	}	
}