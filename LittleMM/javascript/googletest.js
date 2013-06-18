/**
 * Created with IntelliJ IDEA.
 * User: ouamaqing
 * Date: 13-6-14
 * Time: 下午5:59
 * To change this template use File | Settings | File Templates.
 */
casper.start('http://google.fr/');

casper.thenEvaluate(function(term) {
    document.querySelector('input[name="q"]').setAttribute('value', term);
    document.querySelector('form[name="f"]').submit();
}, 'CasperJS');

casper.then(function() {
    // Click on 1st result link
    this.click('h3.r a');
});

casper.then(function() {
    console.log('clicked ok, new location is ' + this.getCurrentUrl());
});

casper.run();
