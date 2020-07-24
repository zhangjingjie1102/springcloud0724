<template>
	<div>
	 <div>
		<el-form :inline="true" :model="formData" class="demo-form-inline">
		  <el-form-item label="登录名">
		    <el-input v-model="formData.username" placeholder="登录名"></el-input>
		  </el-form-item>
		  <el-form-item label="姓名">
		    <el-input v-model="formData.name" placeholder="姓名"></el-input>
		  </el-form-item>
		  <el-form-item label="性别">
		    <el-select v-model="formData.sex" placeholder="性别">
			 <el-option label="全部" value="0"></el-option>
		      <el-option label="男" value="1"></el-option>
		      <el-option label="女" value="2"></el-option>
		    </el-select>
		  </el-form-item>
		   <el-form-item>
		    <el-button type="primary" @click="queryData">查询</el-button>
			 <el-button type="primary" @click="dialogFormVisible = true">添加</el-button>
		  </el-form-item>
		</el-form>
	 </div>
		  
    <el-table
      :data="tableData"
      style="width: 100%">
      <el-table-column
        prop="uid"
        label="id"
        width="180">
      </el-table-column>
      <el-table-column
        prop="username"
        label="用户名"
        width="180">
      </el-table-column>
      <el-table-column
        prop="name"
        label="姓名">
      </el-table-column>
	  <el-table-column
	    prop="driverCard.cardtype"
	    label="驾驶证类型">
	  </el-table-column>
	  <el-table-column
	      fixed="right"
	      label="操作"
	      width="100">
	      <template slot-scope="scope">
	        <el-button @click="edit(scope.row)" type="text" size="small">编辑</el-button>
	        <el-button type="text" size="small" @click="del(scope.row.uid)" >删除</el-button>
	      </template>
	   </el-table-column>
			
    </el-table>
	
	 <div class="block">
	    <span class="demonstration">完整功能</span>
	    <el-pagination
	      @size-change="handleSizeChange"
	      @current-change="handleCurrentChange"
	      :current-page="currentPage"
	      :page-sizes="[1, 5, 9, 13]"
	      :page-size="3"
	      layout="total, sizes, prev, pager, next, jumper"
	      :total="pageTotal">
	    </el-pagination>
	  </div>
	 
	  
	  <!-- Form -->
	  <el-dialog title="收货地址" :visible.sync="dialogFormVisible">
	    <el-form :model="userModel">
	      <el-form-item label="用户名" :label-width="formLabelWidth">
	        <el-input v-model="userModel.username" autocomplete="off"></el-input>
	      </el-form-item>
		  <el-form-item label="房子" :label-width="formLabelWidth">
			  <el-table
			    :data="userModel.roomList"
			    style="width: 100%">
			    <el-table-column
			      prop="name"
			      label="name"
			      width="90">
			    </el-table-column>
			    <el-table-column
			      prop="address"
			      label="地址"
			      width="90">
			    </el-table-column>
				 <el-table-column
				     fixed="right"
				     label="操作"
				     width="100">
				     <template slot-scope="scope">
				       <el-button @click="editRoom(scope.row)" type="text" size="small">编辑</el-button>
				       <el-button type="text" size="small" @click="delRoom(scope.row)" >删除</el-button>
				     </template>
				 </el-table-column>
				  
			 </el-table>
			 
			 <el-form :inline="true" :model="roomData" class="demo-form-inline">
			   <el-form-item label="名称">
			     <el-input v-model="roomData.name" placeholder="名称"></el-input>
			   </el-form-item>
			   <el-form-item label="地址">
			     <el-input v-model="roomData.address" placeholder="地址"></el-input>
			   </el-form-item>
			    <el-form-item>
			 	 <el-button type="primary" @click="addRoom">添加</el-button>
			   </el-form-item>
			 </el-form>
			 
		  </el-form-item>
		  
		  <!--
		  <el-form-item label="姓名" :label-width="formLabelWidth">
		    <el-input v-model="userModel.name" autocomplete="off"></el-input>
		  </el-form-item>
		  <el-form-item label="密码" :label-width="formLabelWidth">
		    <el-input v-model="userModel.password" autocomplete="off"></el-input>
		  </el-form-item>
		  <el-form-item label="生日" :label-width="formLabelWidth">
			<el-date-picker
		        v-model="userModel.birthday"
		        type="date" value-format="yyyy-MM-dd"
		        placeholder="选择日期">
		      </el-date-picker>
		  </el-form-item>
		  
	      <el-form-item label="性别" :label-width="formLabelWidth">
	        <el-select v-model="userModel.sex" placeholder="性别">
	          <el-option label="男" value="1"></el-option>
	          <el-option label="女" value="2"></el-option>
	        </el-select>
	      </el-form-item>
		  -->
		  <el-form-item label="部门" :label-width="formLabelWidth">	
		  <el-select v-model="userModel.depart.id"  placeholder="请选择">
		      <el-option
		        v-for="item in departs"
		        :key="item.id"
		        :label="item.name"
		        :value="item.id">
		      </el-option>
		    </el-select>
		</el-form-item>
		<el-form-item label="爱好" :label-width="formLabelWidth">
			  <div style="margin: 15px 0;"></div>
			  <el-checkbox-group v-model="checkedFavs" >
			    <el-checkbox v-for="fav in favs" :label="fav.id" :key="fav.id">{{fav.name}}</el-checkbox>
			  </el-checkbox-group>
		</el-form-item>
		  
		  <el-form-item label="驾驶证类型" :label-width="formLabelWidth">
		    <el-input v-model="userModel.driverCard.cardtype" autocomplete="off"></el-input>
		  </el-form-item>
		  <el-form-item label="过期时间" :label-width="formLabelWidth">
		  			<el-date-picker
		        v-model="userModel.driverCard.expiredate"
		        type="date" value-format="yyyy-MM-dd"
		        placeholder="选择日期">
		      </el-date-picker>
		  </el-form-item>
	    </el-form>
		
	    <div slot="footer" class="dialog-footer">
	      <el-button @click="dialogFormVisible = false">取 消</el-button>
	      <el-button type="primary" @click="commitData">确 定</el-button>
	    </div>
	  </el-dialog>
	  
	  
	  </div>
  </template>

  <script>
    export default {
		
    	data() {
    		return {
				checkedFavs:[],
				isIndeterminate:true,
				formLabelWidth:'120px',
				dialogFormVisible:false,
				departs:[],
				favs:[],
				roomData:{name:'',address:''},
				userModel:{
					   uid:'',
					    username:'',
					    password:'',
					    name:'',
					    email:'',
					    telephone:'',
					    birthday:'',
					    sex:0,
					    state:0,
					    code:'',
						favouriteList:[],
						roomList:[],
						driverCard:{
							//驾驶证类型
							cardtype:'',				
							  // 过期时间
						    expiredate:''
						},
						depart:{
							id:0,
							name:''
						}
				},
				currentPage:1,
    			tableData: [],
				pageTotal:1,
				formData:{username:'',name:'',sex:0},
    		}
    	},
    	created(){
    		 this.initData(1,5);
			 this.getDeparts();
			 this.getFavs()
    	},
    	methods:{
			handleSizeChange(val) {
			  console.log(`每页 ${val} 条`)
			},
			handleCurrentChange(val) {
			  console.log(`当前页: ${val}`)
			  this.initData(val,5)
			},
			// 获取所有的部门
			getDeparts(){
				 this.axios.post("http://localhost:8200/user/departs").then((resp)=>{
					 this.departs=resp.data;
				 })
			},
			// 获取所有的爱好
			getFavs(){
				 this.axios.post("http://localhost:8200/user/favourites").then((resp)=>{
					 this.favs=resp.data;
				 })
			},
			/* //爱好的全选
			handleCheckAllChange(val) {
			        this.userModel.favouriteList = val ? this.favs : [];
			        this.isIndeterminate = false;
			      },
			// 选中其中一个爱好	  
			handleCheckedCitiesChange(value) {
				let checkedCount = value.length;
				//this.checkAll = checkedCount === this.cities.length;
				//this.isIndeterminate = checkedCount > 0 && checkedCount < this.cities.length;
			 }, */
			//添加房屋
			addRoom(){
				var newRoom = new Object();
				Object.assign(newRoom,this.roomData)
				delete newRoom.id
				this.userModel.roomList.push(newRoom);
				
			},
			//删除房屋
			delRoom(rData){
				let rindex=  this.userModel.roomList.indexOf(rData);
				console.log("要删除的索引 " + rindex) 
				this.userModel.roomList.splice(rindex,1)				 
			},
			editRoom(roomData){
				this.roomData=roomData
			},
			commitData(){
				console.log(this.userModel)
				
				this.userModel.favouriteList=new Array();
				this.checkedFavs.forEach((favId)=>{
					var favObject =  Object();
					//favObject.push("id",favId)
					favObject.id=favId;
					this.userModel.favouriteList.push(favObject)
				})
				console.log(this.userModel.favouriteList)
				//return
				 this.axios.post("http://localhost:8200/user/add",this.userModel).then((resp)=>{
					  if(resp.data==true){
						  this.$message({
							type: 'success',
							message: '添加成功'
						  });   
						this.dialogFormVisible=false
						 
						this.userModel={
							   uid:'',
							    username:'',
							    password:'',
							    name:'',
							    email:'',
							    telephone:'',
							    birthday:'',
							    sex:0,
							    state:0,
							    code:'',
								roomList:[],
								favouriteList:[],
								driverCard:{
									//驾驶证类型
									cardtype:'',				
									  // 过期时间
								    expiredate:''
								},
								depart:{
									id:0,
									name:''
								}
						}
						// 刷新列表
						this.initData(this.currentPage,5)	 
					  }else{
						  this.$message({
							type: 'error',
							message: '添加失败'
						  });     
					  }
				 })
			},
			queryData(){
				this.initData(1,5)
			},
			edit(rowdata){
				
				this.userModel=rowdata
				this.dialogFormVisible=true
			},
			del(id){
				//确认是否删除？
				this.$confirm('此操作将永久删除该数据['+id+'], 是否继续?', '提示', {
				          confirmButtonText: '确定',
				          cancelButtonText: '取消',
				          type: 'warning'
				        }).then(() => {
				         // 用户点击确定的时候执行
						  this.axios.get('http://localhost:8200/user/del',{params:{id:id}})
						  .then((response)=>{
							  if(response.data==true){
								  this.$message({
								    type: 'success',
								    message: '删除成功'
								  });   
								// 刷新列表
								this.initData(this.currentPage,5)	 
							  }else{
								  this.$message({
								    type: 'error',
								    message: '删除失败'
								  });     
							  }
						  })
							
				        }).catch(() => {
						 // 用户点击取消的时候执行	
				          this.$message({
				            type: 'info',
				            message: '已取消删除'
				          });          
						  
				        });
						
			},
    		initData(page,pageSize){
    			 this.axios.get('http://localhost:8200/user/list',
				   {params:{username:this.formData.username,name:this.formData.name,sex:this.formData.sex,page:page,pageSize:pageSize}}).
				   then((res)=>{
    				console.log(res.data);
    				this.tableData=res.data.content;
					this.currentPage=res.data.number;
					this.pageTotal=res.data.totalPages;
    			}) 
    		}
    	}
    }
  </script>