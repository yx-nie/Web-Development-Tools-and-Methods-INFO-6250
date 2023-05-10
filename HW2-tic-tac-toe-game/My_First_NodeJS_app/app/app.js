const express = require('express'); 
const bodyParser=require('body-parser');
const cors=require('cors');

 
const app = express(); //Instantiate an express app, the main work horse of this server
app.use(cors());


const port = 5000; //Save the port number where your server will be listening
 
var storage = [];
var parcel=[];

app.get('/', (req, res)=> {
    res.status(200).send(storage);
 })

app.get('/get', (req, res)=> {
   res.status(200).send(storage);
})
 
app.post('/post',bodyParser.json(), function (req, res) {
    var result="";

    parcel = req.body.data;
    console.log(parcel);

    result = checkWinner(parcel);
    console.log(result);

    if(result!==""){
        storage.push(result);
        console.log(storage);
        res.json([result]);
    }

    
    
    
});

 
app.listen(port, () => { //server starts listening for any attempts from a client to connect at port: {port}
 console.log(`Now listening on port ${port}`); 
});



//functions below

function checkWinner(data){
    var result="";
    var spacefull=false
    //check rows
    if((data[0]===data[1]&&data[1]===data[2])&&(data[0]!="")){
        result=data[0];
    }
    if((data[3]===data[4]&&data[4]===data[5])&&(data[3]!="")){
        result=data[3];
    }
    if((data[6]===data[7]&&data[7]===data[8])&&(data[6]!="")){
        result=data[6];
    }

    //check columns
    if((data[0]===data[3]&&data[3]===data[6])&&(data[0]!="")){
        result=data[0];
    }
    if((data[1]===data[4]&&data[4]===data[7])&&(data[1]!="")){
        result=data[1];
    }
    if((data[2]===data[5]&&data[5]===data[8])&&(data[2]!="")){
        result=data[2];
    }

    //check diagonal
    if((data[0]===data[4]&&data[4]===data[8])&&(data[0]!="")){
        result=data[0];
    }
    if((data[2]===data[4]&&data[4]===data[6])&&(data[2]!="")){
        result=data[2];
    }
    
    var count=0;
    for(var i=0; i<data.length; i++){
        if(data[i]!=""){
            count++;
        }
    }

    if(count===9){
        spacefull=!spacefull;
    }

    if((spacefull)&&(result==="")){
        return "Draw";
    }
        
    return result;
}
