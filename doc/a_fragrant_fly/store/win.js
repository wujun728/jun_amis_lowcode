export default{
		state: {
			screen:{
				mode:true,//窗口宽度比高度 长
				height:0,//窗口高度
				width:0,//窗口宽度
			}
		},getters:{
			screen(state){
					return state.screen;
			}
		},mutations: {
			screen(state,screen){
				var width=screen.width || 720;
				var height=screen.height || 1440;
				var mode=true;
				if(width<height){
					mode=false;
				}
				state.screen={
					mode,
					width,
					height
				};
			}
			
		},actions: {

		}
}