var app = app || {};
var QuestionList = Backbone.Collection.extend({
// Reference to this collection’s model.
model: app.Question,
localStorage: new Backbone.LocalStorage('questions-backbone'),
// Filter down the list of all todo items that are finished.
answered: function() {
return this.filter(function( question ) {
return question.get('answered');
});
},
remaining: function() {
return this.without.apply( this, this.answered() );
},
nextOrder: function() {
if ( !this.length ) {
return 1;
}
return this.last().get('order') + 1;
},
// Todos are sorted by their original insertion order.
comparator: function( question ) {
return question.get('order');
}
});
// Create our global collection of **Todos**.
app.Todos = new TodoList();