 
package co.edu.uniandes.csw.carritodecompras.master.logic.api;

import co.edu.uniandes.csw.carritodecompras.master.logic.dto.CarritoDeComprasMasterDTO;

public interface _ICarritoDeComprasMasterLogicService {

	public CarritoDeComprasMasterDTO createMasterCarritoDeCompras(CarritoDeComprasMasterDTO detail);
    public void updateMasterCarritoDeCompras(CarritoDeComprasMasterDTO detail);
	public void deleteMasterCarritoDeCompras(Long id); 
	public CarritoDeComprasMasterDTO getMasterCarritoDeCompras(Long id);
        
}