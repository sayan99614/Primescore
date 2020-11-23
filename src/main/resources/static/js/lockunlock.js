
	function unlock(id,status){
		swal({
			  title: "Are you sure?",
			  text: "Once you unlock your class students can join in your class",
			  icon: "success",
			  buttons: true,
			  dangerMode: false,
			})
			.then((willDelete) => {
			  if (willDelete) {
				$.ajax({
					url:"/teacher/status/"+id+"/"+status,
					type:"GET",
					success:function(data, textStatus, jqXHR){ 
					console.log("success");
					location.reload();
					
				  },
				  error(xhr,status,error){ 
					  console.log("failure"); 
					  
				  }
					
				});
			    }
			  else {
			    swal("your class is still locked students can't join");
			  }
			});
		
	}
  
  
		function islocked(cid,status){
			swal({
				  title: "Are you sure?",
				  text: "Once you lock your class students can't join in your class",
				  icon: "warning",
				  buttons: true,
				  dangerMode: true,
				})
				.then((willDelete) => {
				  if (willDelete) {
					$.ajax({
					url:"/teacher/status/"+cid+"/"+status,
					type:"GET",
					success:function(data, textStatus, jqXHR){ 
					console.log("success");
					location.reload();
					
				  },
				  error(xhr,status,error){ 
					  console.log("failure"); 
					  
				  }
					
				});
				    }
				  else {
				    swal("your class is still unlocked students can join");
				  }
				});
		}
		
		