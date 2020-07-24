 <template>
	 <div>
		
		 
    <el-table
      :data="tableData"
      style="width: 100%" @selection-change="changeFun">
	  <el-table-column
	    prop="uid"
	    label="id"
	    width="50"
		type="selection">
		</el-table-column>
		<el-table-column
		  prop="uid"
		  label="uid"
		  width="180">
		</el-table-column>
		
	  
      <el-table-column
        prop="date"
        label="日期"
        width="180">
      </el-table-column>
      <el-table-column
        prop="name"
        label="姓名"
        width="180">
      </el-table-column>
      <el-table-column
        prop="address"
        label="地址">
      </el-table-column>
	  <el-table-column
	    prop="address"
	    label="地址">
		  <template slot-scope="scope">
		<input type="button" @click="edit(scope.row.uid)"/>
		</template>
	  </el-table-column>
    </el-table>
	</div>
  </template>

  <script>
    export default {
      data() {
        return {
		  form:{},
          tableData: [],
		  gridData:[],
		  testData: '',
		  dialogFormVisible: false,
        }
      },
	  created() {
	  	this.testPost()
	  },
	  methods:{
		  mytest:function(data) {
			 // alert('bb' + data)
		  }
		  ,
		  testPost:function(){
			  this.axios.post("http://localhost:8200/user/list",{page:1,pageSize:5}).then(response=>{
				  console.log(response.data.content)
				 this.tableData=response.data.content;
				  
			  })
		  } ,
		  edit:function(val){
			 this.dialogFormVisible=true;
			//alert(val)  
		  },
		  changeFun:function(val) {
			 this.multipleSelection = val;
			val.forEach((itme)=>{
				alert(itme.uid)
			}) 
		  }
	  }
	  
    }
  </script>