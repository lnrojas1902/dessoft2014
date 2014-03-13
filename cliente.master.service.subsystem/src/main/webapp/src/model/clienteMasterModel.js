define(['model/_clienteMasterModel'], function() { 
    App.Model.ClienteMasterModel = App.Model._ClienteMasterModel.extend({
		initialize: function() {
            this.on('invalid', function(model,error) {
                Backbone.trigger('cliente-master-model-error', error);
            });
        },
        validate: function(attrs, options){
        	var modelMaster = new App.Model.ClienteModel();
        	if(modelMaster.validate){
            	return modelMaster.validate(attrs.clienteEntity,options);
            }
        }
    });

    App.Model.ClienteMasterList = App.Model._ClienteMasterList.extend({
        model: App.Model.ClienteMasterModel
    });

    return  App.Model.ClienteMasterModel;

});