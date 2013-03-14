var app = app || {};
app.Question = Backbone.Model.extend({
defaults: {
title: ’’,
answered: false
},
toggle: function() {
this.save({
answered: !this.get('answered')
});
}
});