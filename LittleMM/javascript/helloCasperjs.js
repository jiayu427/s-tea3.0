var casper = require('casper').create();

//casper.on('resource.requested',function(request){
//    var str=request.headers;
//    for(var key in str){
//        for(var k in str[key]){
//            this.echo(k+"->"+str[key][k]);
//        }
//    }
//});
casper.on('resource.received',function(resource){
    var str=resource.headers;
    for(var key in str){
        for(var k in str[key]){
            this.echo(k+"->"+str[key][k]);
        }
    }
});

casper.start('http://www.baidu.com',function(){
    this.sendKeys('#kw','北京');
    this.click('#su');
});

casper.then(function(){
   casper.echo(this.getTitle());
});

casper.run();
