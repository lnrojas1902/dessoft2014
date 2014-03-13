
package co.edu.uniandes.csw.carritodecompras.logic.api;

import java.util.List; 

import co.edu.uniandes.csw.carritodecompras.logic.dto.CarritoDeComprasDTO;

public interface _ICarritoDeComprasLogicService {

	public CarritoDeComprasDTO createCarritoDeCompras(CarritoDeComprasDTO detail);
	public List<CarritoDeComprasDTO> getCarritoDeComprass();
	public CarritoDeComprasDTO getCarritoDeCompras(Long id);
	public void deleteCarritoDeCompras(Long id);
	public void updateCarritoDeCompras(CarritoDeComprasDTO detail);
	
}