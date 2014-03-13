define(['model/carritoDeComprasModel'], function(carritoDeComprasModel) {
    App.Controller._CarritoDeComprasController = Backbone.View.extend({
        initialize: function(options) {
            this.modelClass = options.modelClass;
            this.listModelClass = options.listModelClass;
            this.showEdit = true;
            this.showDelete = true;
            this.editTemplate = _.template($('#carritoDeCompras').html());
            this.listTemplate = _.template($('#carritoDeComprasList').html());
            if (!options || !options.componentId) {
                this.componentId = _.random(0, 100) + "";
            }else{
				this.componentId = options.componentId;
		    }
            var self = this;
            Backbone.on(this.componentId + '-' + 'carritoDeCompras-create', function(params) {
                self.create(params);
            });
            Backbone.on(this.componentId + '-' + 'carritoDeCompras-list', function(params) {
                self.list(params);
            });
            Backbone.on(this.componentId + '-' + 'carritoDeCompras-edit', function(params) {
                self.edit(params);
            });
            Backbone.on(this.componentId + '-' + 'carritoDeCompras-delete', function(params) {
                self.destroy(params);
            });
            Backbone.on(this.componentId + '-' + 'post-carritoDeCompras-delete', function(params) {
                self.list(params);
            });
            Backbone.on(this.componentId + '-' + 'carritoDeCompras-save', function(params) {
                self.save(params);
            });
            if(self.postInit){
            	self.postInit();
            }
        },
        create: function() {
            if (App.Utils.eventExists(this.componentId + '-' +'instead-carritoDeCompras-create')) {
                Backbone.trigger(this.componentId + '-' + 'instead-carritoDeCompras-create', {view: this});
            } else {
                Backbone.trigger(this.componentId + '-' + 'pre-carritoDeCompras-create', {view: this});
                this.currentCarritoDeComprasModel = new this.modelClass();
                this._renderEdit();
                Backbone.trigger(this.componentId + '-' + 'post-carritoDeCompras-create', {view: this});
            }
        },
        list: function(params) {
            if (params) {
                var data = params.data;
            }
            if (App.Utils.eventExists(this.componentId + '-' +'instead-carritoDeCompras-list')) {
                Backbone.trigger(this.componentId + '-' + 'instead-carritoDeCompras-list', {view: this, data: data});
            } else {
                Backbone.trigger(this.componentId + '-' + 'pre-carritoDeCompras-list', {view: this, data: data});
                var self = this;
				if(!this.carritoDeComprasModelList){
                 this.carritoDeComprasModelList = new this.listModelClass();
				}
                this.carritoDeComprasModelList.fetch({
                    data: data,
                    success: function() {
                        self._renderList();
                        Backbone.trigger(self.componentId + '-' + 'post-carritoDeCompras-list', {view: self});
                    },
                    error: function(mode, error) {
                        Backbone.trigger(self.componentId + '-' + 'error', {event: 'carritoDeCompras-list', view: self, error: error});
                    }
                });
            }
        },
        edit: function(params) {
            var id = params.id;
            var data = params.data;
            if (App.Utils.eventExists(this.componentId + '-' +'instead-carritoDeCompras-edit')) {
                Backbone.trigger(this.componentId + '-' + 'instead-carritoDeCompras-edit', {view: this, id: id, data: data});
            } else {
                Backbone.trigger(this.componentId + '-' + 'pre-carritoDeCompras-edit', {view: this, id: id, data: data});
                if (this.carritoDeComprasModelList) {
                    this.currentCarritoDeComprasModel = this.carritoDeComprasModelList.get(id);
                    this._renderEdit();
                    Backbone.trigger(this.componentId + '-' + 'post-carritoDeCompras-edit', {view: this, id: id, data: data});
                } else {
                    var self = this;
                    this.currentCarritoDeComprasModel = new this.modelClass({id: id});
                    this.currentCarritoDeComprasModel.fetch({
                        data: data,
                        success: function() {
                            self._renderEdit();
                            Backbone.trigger(self.componentId + '-' + 'post-carritoDeCompras-edit', {view: this, id: id, data: data});
                        },
                        error: function() {
                            Backbone.trigger(self.componentId + '-' + 'error', {event: 'carritoDeCompras-edit', view: self, id: id, data: data, error: error});
                        }
                    });
                }
            }
        },
        destroy: function(params) {
            var id = params.id;
            var self = this;
            if (App.Utils.eventExists(this.componentId + '-' +'instead-carritoDeCompras-delete')) {
                Backbone.trigger(this.componentId + '-' + 'instead-carritoDeCompras-delete', {view: this, id: id});
            } else {
                Backbone.trigger(this.componentId + '-' + 'pre-carritoDeCompras-delete', {view: this, id: id});
                var deleteModel;
                if (this.carritoDeComprasModelList) {
                    deleteModel = this.carritoDeComprasModelList.get(id);
                } else {
                    deleteModel = new this.modelClass({id: id});
                }
                deleteModel.destroy({
                    success: function() {
                        Backbone.trigger(self.componentId + '-' + 'post-carritoDeCompras-delete', {view: self, model: deleteModel});
                    },
                    error: function() {
                        Backbone.trigger(self.componentId + '-' + 'error', {event: 'carritoDeCompras-delete', view: self, error: error});
                    }
                });
            }
        },
        save: function() {
            var self = this;
            var model = $('#' + this.componentId + '-carritoDeComprasForm').serializeObject();
            if (App.Utils.eventExists(this.componentId + '-' +'instead-carritoDeCompras-save')) {
                Backbone.trigger(this.componentId + '-' + 'instead-carritoDeCompras-save', {view: this, model : model});
            } else {
                Backbone.trigger(this.componentId + '-' + 'pre-carritoDeCompras-save', {view: this, model : model});
                this.currentCarritoDeComprasModel.set(model);
                this.currentCarritoDeComprasModel.save({},
                        {
                            success: function(model) {
                                Backbone.trigger(self.componentId + '-' + 'post-carritoDeCompras-save', {model: self.currentCarritoDeComprasModel});
                            },
                            error: function(error) {
                                Backbone.trigger(self.componentId + '-' + 'error', {event: 'carritoDeCompras-save', view: self, error: error});
                            }
                        });
            }
        },
        _renderList: function() {
            var self = this;
            this.$el.slideUp("fast", function() {
                self.$el.html(self.listTemplate({carritoDeComprass: self.carritoDeComprasModelList.models, componentId: self.componentId, showEdit : self.showEdit , showDelete : self.showDelete}));
                self.$el.slideDown("fast");
            });
        },
        _renderEdit: function() {
            var self = this;
            this.$el.slideUp("fast", function() {
                self.$el.html(self.editTemplate({carritoDeCompras: self.currentCarritoDeComprasModel, componentId: self.componentId , showEdit : self.showEdit , showDelete : self.showDelete
 
				}));
                self.$el.slideDown("fast");
            });
        }
    });
    return App.Controller._CarritoDeComprasController;
});