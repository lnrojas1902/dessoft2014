define(['model/clienteModel'], function(clienteModel) {
    App.Controller._ClienteController = Backbone.View.extend({
        initialize: function(options) {
            this.modelClass = options.modelClass;
            this.listModelClass = options.listModelClass;
            this.showEdit = true;
            this.showDelete = true;
            this.editTemplate = _.template($('#cliente').html());
            this.listTemplate = _.template($('#clienteList').html());
            if (!options || !options.componentId) {
                this.componentId = _.random(0, 100) + "";
            }else{
				this.componentId = options.componentId;
		    }
            var self = this;
            Backbone.on(this.componentId + '-' + 'cliente-create', function(params) {
                self.create(params);
            });
            Backbone.on(this.componentId + '-' + 'cliente-list', function(params) {
                self.list(params);
            });
            Backbone.on(this.componentId + '-' + 'cliente-edit', function(params) {
                self.edit(params);
            });
            Backbone.on(this.componentId + '-' + 'cliente-delete', function(params) {
                self.destroy(params);
            });
            Backbone.on(this.componentId + '-' + 'post-cliente-delete', function(params) {
                self.list(params);
            });
            Backbone.on(this.componentId + '-' + 'cliente-save', function(params) {
                self.save(params);
            });
            if(self.postInit){
            	self.postInit();
            }
        },
        create: function() {
            if (App.Utils.eventExists(this.componentId + '-' +'instead-cliente-create')) {
                Backbone.trigger(this.componentId + '-' + 'instead-cliente-create', {view: this});
            } else {
                Backbone.trigger(this.componentId + '-' + 'pre-cliente-create', {view: this});
                this.currentClienteModel = new this.modelClass();
                this._renderEdit();
                Backbone.trigger(this.componentId + '-' + 'post-cliente-create', {view: this});
            }
        },
        list: function(params) {
            if (params) {
                var data = params.data;
            }
            if (App.Utils.eventExists(this.componentId + '-' +'instead-cliente-list')) {
                Backbone.trigger(this.componentId + '-' + 'instead-cliente-list', {view: this, data: data});
            } else {
                Backbone.trigger(this.componentId + '-' + 'pre-cliente-list', {view: this, data: data});
                var self = this;
				if(!this.clienteModelList){
                 this.clienteModelList = new this.listModelClass();
				}
                this.clienteModelList.fetch({
                    data: data,
                    success: function() {
                        self._renderList();
                        Backbone.trigger(self.componentId + '-' + 'post-cliente-list', {view: self});
                    },
                    error: function(mode, error) {
                        Backbone.trigger(self.componentId + '-' + 'error', {event: 'cliente-list', view: self, error: error});
                    }
                });
            }
        },
        edit: function(params) {
            var id = params.id;
            var data = params.data;
            if (App.Utils.eventExists(this.componentId + '-' +'instead-cliente-edit')) {
                Backbone.trigger(this.componentId + '-' + 'instead-cliente-edit', {view: this, id: id, data: data});
            } else {
                Backbone.trigger(this.componentId + '-' + 'pre-cliente-edit', {view: this, id: id, data: data});
                if (this.clienteModelList) {
                    this.currentClienteModel = this.clienteModelList.get(id);
                    this._renderEdit();
                    Backbone.trigger(this.componentId + '-' + 'post-cliente-edit', {view: this, id: id, data: data});
                } else {
                    var self = this;
                    this.currentClienteModel = new this.modelClass({id: id});
                    this.currentClienteModel.fetch({
                        data: data,
                        success: function() {
                            self._renderEdit();
                            Backbone.trigger(self.componentId + '-' + 'post-cliente-edit', {view: this, id: id, data: data});
                        },
                        error: function() {
                            Backbone.trigger(self.componentId + '-' + 'error', {event: 'cliente-edit', view: self, id: id, data: data, error: error});
                        }
                    });
                }
            }
        },
        destroy: function(params) {
            var id = params.id;
            var self = this;
            if (App.Utils.eventExists(this.componentId + '-' +'instead-cliente-delete')) {
                Backbone.trigger(this.componentId + '-' + 'instead-cliente-delete', {view: this, id: id});
            } else {
                Backbone.trigger(this.componentId + '-' + 'pre-cliente-delete', {view: this, id: id});
                var deleteModel;
                if (this.clienteModelList) {
                    deleteModel = this.clienteModelList.get(id);
                } else {
                    deleteModel = new this.modelClass({id: id});
                }
                deleteModel.destroy({
                    success: function() {
                        Backbone.trigger(self.componentId + '-' + 'post-cliente-delete', {view: self, model: deleteModel});
                    },
                    error: function() {
                        Backbone.trigger(self.componentId + '-' + 'error', {event: 'cliente-delete', view: self, error: error});
                    }
                });
            }
        },
        save: function() {
            var self = this;
            var model = $('#' + this.componentId + '-clienteForm').serializeObject();
            if (App.Utils.eventExists(this.componentId + '-' +'instead-cliente-save')) {
                Backbone.trigger(this.componentId + '-' + 'instead-cliente-save', {view: this, model : model});
            } else {
                Backbone.trigger(this.componentId + '-' + 'pre-cliente-save', {view: this, model : model});
                this.currentClienteModel.set(model);
                this.currentClienteModel.save({},
                        {
                            success: function(model) {
                                Backbone.trigger(self.componentId + '-' + 'post-cliente-save', {model: self.currentClienteModel});
                            },
                            error: function(error) {
                                Backbone.trigger(self.componentId + '-' + 'error', {event: 'cliente-save', view: self, error: error});
                            }
                        });
            }
        },
        _renderList: function() {
            var self = this;
            this.$el.slideUp("fast", function() {
                self.$el.html(self.listTemplate({clientes: self.clienteModelList.models, componentId: self.componentId, showEdit : self.showEdit , showDelete : self.showDelete}));
                self.$el.slideDown("fast");
            });
        },
        _renderEdit: function() {
            var self = this;
            this.$el.slideUp("fast", function() {
                self.$el.html(self.editTemplate({cliente: self.currentClienteModel, componentId: self.componentId , showEdit : self.showEdit , showDelete : self.showDelete
 
				}));
                self.$el.slideDown("fast");
            });
        }
    });
    return App.Controller._ClienteController;
});