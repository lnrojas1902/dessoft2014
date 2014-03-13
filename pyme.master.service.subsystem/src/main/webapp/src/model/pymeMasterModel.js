define(['model/_pymeMasterModel'], function() { 
    App.Model.PymeMasterModel = App.Model._PymeMasterModel.extend({
		initialize: function() {
            this.on('invalid', function(model,error) {
                Backbone.trigger('pyme-master-model-error', error);
            });
        },
        validate: function(attrs, options){
        	var modelMaster = new App.Model.PymeModel();
        	if(modelMaster.validate){
            	return modelMaster.validate(attrs.pymeEntity,options);
            }
        }
    });

    App.Model.PymeMasterList = App.Model._PymeMasterList.extend({
        model: App.Model.PymeMasterModel
    });

    return  App.Model.PymeMasterModel;

});