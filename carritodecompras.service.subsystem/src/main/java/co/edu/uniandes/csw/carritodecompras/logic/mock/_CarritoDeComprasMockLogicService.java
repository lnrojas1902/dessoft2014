
package co.edu.uniandes.csw.carritodecompras.logic.mock;
import java.util.ArrayList;
import java.util.List;

import co.edu.uniandes.csw.carritodecompras.logic.dto.CarritoDeComprasDTO;
import co.edu.uniandes.csw.carritodecompras.logic.api._ICarritoDeComprasLogicService;

public abstract class _CarritoDeComprasMockLogicService implements _ICarritoDeComprasLogicService {

	private Long id= new Long(1);
	protected List<CarritoDeComprasDTO> data=new ArrayList<CarritoDeComprasDTO>();

	public CarritoDeComprasDTO createCarritoDeCompras(CarritoDeComprasDTO carritoDeCompras){
		id++;
		carritoDeCompras.setId(id);
		return carritoDeCompras;
    }

	public List<CarritoDeComprasDTO> getCarritoDeComprass(){
		return data; 
	}

	public CarritoDeComprasDTO getCarritoDeCompras(Long id){
		for(CarritoDeComprasDTO d:data){
			if(d.getId().equals(id)){
				return d;
			}
		}
		return null;
	}

	public void deleteCarritoDeCompras(Long id){
	    CarritoDeComprasDTO delete=null;
		for(CarritoDeComprasDTO d:data){
			if(d.getId().equals(id)){
				delete=d;
			}
		}
		if(delete!=null){
			data.remove(delete);
		} 
	}

	public void updateCarritoDeCompras(CarritoDeComprasDTO carritoDeCompras){
	    CarritoDeComprasDTO delete=null;
		for(CarritoDeComprasDTO d:data){
			if(d.getId().equals(id)){
				delete=d;
			}
		}
		if(delete!=null){
			data.remove(delete);
			data.add(carritoDeCompras);
		} 
	}	
}