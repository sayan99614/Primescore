$(document).ready(function(){
    console.log("ready")
   $(this).on("click","#add_",function(){
       var html='<div class="card rmv mb-3"><div class="card-body shadow"><div class="w-25"><select class="form-control form-control-sm "><option>MCQ</option><option>Short Answer</option></select></div><div class="form-group mt-2"><label for="exampleFormControlTextarea1">Enter Question</label><textarea class="form-control" id="exampleFormControlTextarea1" rows="3"></textarea></div><label for="exampleFormControlTextarea1">Select Correct Response</label><div class="offset-sm-1 w-75"><div class="input-group"><div class="input-group-prepend"><div class="input-group-text"><input type="radio" aria-label="Radio button for following text input" name="answer"></div></div><input type="text" placeholder="enter answer" class="form-control" aria-label="Text input with radio button"></div><br><div class="input-group"><div class="input-group-prepend"><div class="input-group-text"><input type="radio" aria-label="Radio button for following text input" name="answer"></div></div><input type="text" placeholder="enter answer" class="form-control" aria-label="Text input with radio button"></div><br><div class="input-group"><div class="input-group-prepend"><div class="input-group-text"><input type="radio" aria-label="Radio button for following text input" name="answer"></div></div><input type="text" placeholder="enter answer" class="form-control" aria-label="Text input with radio button"></div><br><div class="input-group"><div class="input-group-prepend"><div class="input-group-text"> <input type="radio" aria-label="Radio button for following text input" name="answer"></div></div><input type="text" placeholder="enter answer" class="form-control" aria-label="Text input with radio button"> </div></div>  <div class="d-flex justify-content-between mt-3"><div><label for="exampleFormControlTextarea1">Grade</label><br><span><input style="width: 40px;" type="number" placeholder="1">&nbsp;points</span></div><div><button id="add_" type="button" class="btn btn-outline-info btn-round-sm btn-sm"><span class="fas fa-plus fa-lg"></span>&nbsp;Add more</button></div></div></div>&nbsp;<button id="remove" class="btn btn-sm btn-outline-danger">Remove</button></div>';
       $(".append_html").append(html);
       
   }); 
    
     $(this).on("click","#remove",function(){
        var target=$(this).parent();
        target.remove();
    });
    
});