function resume() { //12 card show
    $.getJSON("/test/getCardList")
            .done(function (data) {
                 console.log("-----> this is data img "+ data);
                 
                showCardsOnTable("#gameBoard", data);
       
            }).fail(function () {
        console.log("Not Found");
    });
};

function showCardsOnTable(tableId, data) {// 12 card show on table
    // To clear all rows inside the table
   // $(tableId).empty();
    // Add row based on return data
    var loc =-1;
    var row = $("<tr />");
    //$(tableId).append(row);
    for (var i = 0 ; i < data.length ; i++) {
        loc ++;
        if (i % 3 === 0) {
            row = $("<tr />");
            $(tableId).append(row);
        }
        drawRow(data[i], row, loc);
        console.log("data i : "+loc);
    }
     $(tableId).append(row);
};


function drawRow(cardData, row, loc) {
    console.log("----> card data : "+cardData.cardId+" "+cardData.pImg);
    var $td=$('<td>');
    var onclickStr="addToSet("+cardData.cardId+","+loc+")";
    var $img=$("<image src='image/"+cardData.pImg+"' id="+cardData.cardId+"' onclick="+onclickStr+"/>");

    $td.append($img);
    row.append($td);
}


function showset(tableId, data) {// 12 card show on table
    console.log("this is from showset : "+ data.showImg);
    var row = $("<tr />");
    $(tableId).append(row);
    for (var i = 0 ; i < 3 ; i++) {
        showsetrow(data[i], row);
             $(tableId).append(row);

    }
   //  $(tableId).append(row);
};


function showsetrow(cardData, row) {
    //console.log("----> card data : "+cardData.showcardId+" "+cardData.showcardName);
    var $td=$('<td>');
    var $img=$("<image src='image/"+cardData.showImg+"'");
    row.append($td);
    $td.append($img);
}


var locationOfCard = new Array();
var lstcardId = new Array();
var count = -1;
var selectedCards = [3];
//var position;
function addToSet(cardId,loc)
{
    console.log("-----> "+loc);
    count++;
    lstcardId[count] = cardId;
     selectedCards[count - 1] = cardId;
    locationOfCard[count] = loc;
    
    if(count == 2)   
    {
            
   $.ajax({
    type: 'Post', // it's easier to read GET request parameters
    url: 'getCardList',
    dataType: 'JSON',
    data: { 
      //loadProds: 'cardId',
      
          cardId1 : lstcardId[0],
          cardId2 : lstcardId[1],
          cardId3 : lstcardId[2],
          loc1 : locationOfCard[0],
          loc2 : locationOfCard[1],
          loc3 : locationOfCard[2]
      
    },
    success: function(data) {
        alert('Congradulation! You found the set!');
        
        var valid = true;
                    if (valid) {
                        count = -1;
                        selectedCards = [];
       // showCardsOnTable("#table", data );
       console.log("this is from success : "+data.showImg);
                    showset("#setcardlist", data );  
                    

                    } else {
                        //showCardsOnTable("#setcardlist",data);
                    }
    },
    error: function(data) {
        alert('Opps! Wrong Set, Please Try Again!');
        count = -1;
        $("#chkbox").prop('checked', false);
    }
   
     
    });
 
            
    }

}