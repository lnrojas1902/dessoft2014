define(['model/_carritoDeComprasMasterModel'], function() { 
    App.Model.CarritoDeComprasMasterModel = App.Model._CarritoDeComprasMasterModel.extend({
		initialize: function() {
            this.on('invalid', function(model,error) {
                Backbone.trigger('carritoDeCompras-master-model-error', error);
            });
        },
        validate: function(attrs, options){
        	var modelMaster = new App.Model.CarritoDeComprasModel();
        	if(modelMaster.validate){
            	return modelMaster.validate(attrs.carritoDeComprasEntity,options);
            }
        }
    });

    App.Model.CarritoDeComprasMasterList = App.Model._CarritoDeComprasMasterList.extend({
        model: App.Model.CarritoDeComprasMasterModel
    });

    return  App.Model.CarritoDeComprasMasterModel;

});