define([], function() {
    App.Model._PymeMasterModel = Backbone.Model.extend({
     
    });

    App.Model._PymeMasterList = Backbone.Collection.extend({
        model: App.Model._PymeMasterModel,
        initialize: function() {
        }

    });
    return App.Model._PymeMasterModel;
    
});