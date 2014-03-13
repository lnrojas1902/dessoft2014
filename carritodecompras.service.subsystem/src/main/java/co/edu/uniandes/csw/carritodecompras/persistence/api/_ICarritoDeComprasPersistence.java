
package co.edu.uniandes.csw.carritodecompras.persistence.api;

import java.util.List; 

import co.edu.uniandes.csw.carritodecompras.logic.dto.CarritoDeComprasDTO;

public interface _ICarritoDeComprasPersistence {

	public CarritoDeComprasDTO createCarritoDeCompras(CarritoDeComprasDTO detail);
	public List<CarritoDeComprasDTO> getCarritoDeComprass();
	public CarritoDeComprasDTO getCarritoDeCompras(Long id);
	public void deleteCarritoDeCompras(Long id);
	public void updateCarritoDeCompras(CarritoDeComprasDTO detail);
	
}