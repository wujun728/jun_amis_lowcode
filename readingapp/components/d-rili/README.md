## 2020-09-06 d-rili 自定义日历


## 【----------------------------------------------------------------】


## 【亲测有效：看完将掌握：日历js渲染的方法 (不难)】


## 【----------------------------------------------------------------】

（收藏不迷路，感谢支持）
````text
掌握：

1.月初月末每天的日期

2.item原生组件，如何判断左右滑动：显示对应的年份

````


## vue代码
````vue
<template>
	<view>
		<!--  -->
		<view class="riliWrapper">
			<view class="riliWrapperBox">
				<!-- 日历 -->
				<view class="signWrapperCalendar" v-if="true">
					<view class="signWrapperCalendarBox">
						<!--  -->
						<swiper @change="_onClickSlideApi" duration="200" :current="slideDataListIndex" circular style="height:550rpx">
							<swiper-item class="swiper-item" v-for="(calendar,indexa) in 3" :key="indexa" >
							<view class="signWrapperCalendarBoxTop" >
								{{year}}年{{month<10?'0'+month:month}}月
							</view>
							<view class="signWrapperCalendarBoxCenter">
								<view class="signWrapperCalendarBoxCenterBox">
									<view class="signWrapperCalendarBoxCenterBoxTop">
										<div class="week-number">
											<span v-for="(item,index) in weekList" :style="{color:(index==0||index==weekList.length-1)&&themeColor}" :key="index">{{item}}</span>
										</div>
									</view>
									<view class="signWrapperCalendarBoxCenterBoxFooter">
										<view class="each-day" v-for="(dayTime,idx) in dayList" :key="idx" >
											<view :class="dayTime!=day+2 ?'eachDayBox':'eachDayBoxCheck'" v-if="day">
												<view class="eachDayBoxBox" :style="dayTime==day?'border-bottom: 1rpx solid #4E6EF2;':''">
													{{dayTime?dayTime:''}}
												</view>
												
											</view>
										</view>
									</view>
								</view>
							</view>
							
							
						</swiper-item>
						</swiper>
						<!--  -->
					</view>
					
					
				</view>
			</view>
			
			
			
		</view>
		<!--  -->
	</view>
</template>

````

## js代码

````js
<script>
	export default {
		data() {
			return {
				weekList: ['日', '一', '二', '三', '四', '五', '六'],
				//	月份数组【2020-08-01	  2020-09-01   2020-10-01】
				slideDataList:[],
				//	月份数组的索引
				slideDataListIndex:1,
				year:2020,
				month:10,
				day:10,
				dayList:[],
				start_time:'',	//	月初的时间戳
				end_time:'',	//	月末的时间戳
			};
		},
		created() {
			console.log('created')
			
			this._onLoad()
		},
		methods:{
			async _onLoad() {
				//	获取当前时间 	赋值年，月
				await this.initTime()
				
				//	更新日历
				await this._runMonth()
			},
			//	初始化时间
			initTime() {
				var nowTimeData = this._getTimeNowApi()
				
				this.year = nowTimeData.year
				this.month = nowTimeData.month
				this.day = nowTimeData.day
				
				// 今日时间为：2020-9-16
				console.log('今日时间为：'+this.year+'-'+this.month+'-'+this.day)
			},
			
			async _runMonth() {
				console.log('==============================================================')
				
				//	获取当前月的每一天的数据	1~31
				await this.initApi()
				
				//	根据当前选择的年月，更新start_time   end_time
				await this._timeApi()
				
				console.log(this.start_time)
				console.log(this.end_time)
				//	更新记录
				// await this.onClickSignLog()
				
				let dataWeek = await this.getweek(this._getNowApi());
				
				// console.log(this._getNowApi())
				this.slideDataList[0] = []
				this.slideDataList[1] = dataWeek
				this.slideDataList[2] = []
				
				console.log(this.slideDataList)
				
				
				console.log('==============================================================')
			},
			
			_getTimeNowApi() {
				//	初始化时间
				var date = new Date();
				
				var year = date.getFullYear();
				var month = parseInt(date.getMonth()+1);
				var day = date.getDate();
				
				if (month < 10) {
					month = '0' + month
				}
				if (day < 10) {
					day = '0' + day
				}
				
				let returnData = {
					year: year,
					month:parseInt(month),
					day:day,
				}
				
				return returnData
			},
			
			//	滑动日历触发（左右滑动）
			_onClickSlideApi(e) {
				
				let current = e.detail.current
				
				let oldIndex = this.slideDataListIndex
				
				this.slideDataListIndex = current
				
				if(oldIndex - current == -1 || oldIndex - current == 2){
					console.log('向右滑动前一个月')
					if (this.month == 12) {
						this.year = this.year + 1
						this.month = 1
					} else {
						this.month = this.month+1
					}
					
				} else {
					console.log('向左滑动后退一个月')
					if (this.month == 1) {
						this.year = this.year - 1
						this.month = 12
					} else {
						this.month = this.month - 1
					}
				}
				
				this._runMonth()
			},
			
			_getNowApi() {
				console.log('当前日期：'+this.year+'-'+this.month+'-'+this.day)
				
				let data = {
					Day: 1,
					Month: this.month,
					Year: this.year
				}
				
				console.log(data)
				return data
			},
			//	获取当前月的每一天的数据
			initApi() {
				this.dayList = this.createDayList(this.month, this.year);
			},
			
			//创建每个月日历数据，传入月份1号前面用null填充
			createDayList(month, year) {
			    const count = this.getDayNum(month, year),
			        _week = new Date(year + '/' + month + '/1').getDay();
			    let list = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28]
				
			    for (let i = 29; i <= count; i++) {
			        list.push(i)
			    }
			    for (let i = 0; i < _week; i++) {
			        list.unshift(null)
			    }
				
			    return list;
			},
			//计算传入月份有多少天
			getDayNum(month, year) {
			    let dayNum = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31]
			
			    if ((year % 4 === 0) && (year % 100 !== 0) || (year % 400 === 0)) {
			        dayNum[1] = 29;
			    }
			    return dayNum[month - 1]
			},
			//	传时间获取月初月末时间
			_timeApi() {
				
				let startDate = this.year+'-'+this.month+'-'+this.day
				
				let startData = this._timeMonthStartApi(startDate+' 00:00:00')
				
				this.start_time = startData.time_int
				
				let endData = this._timeMonthEndApi(startDate+' 00:00:00')
				
				this.end_time = endData.time_int
			},
			_timeMonthStartApi(timeDate) {
				var date = new Date(timeDate);
				
				date.setDate(1);
				
				var month = parseInt(date.getMonth()+1);
				
				var day = date.getDate();
				if (month < 10) {
				    month = '0' + month
				}
				if (day < 10) {
				    day = '0' + day
				}
				
				let timeDateStart = date.getFullYear() + '-' + month + '-' + day;
				
				let returnData = {
					time_type:'start_time',
					time_int: Date.parse(timeDateStart+' 00:00:00')/1000,
					time_date: timeDateStart,
					year:date.getFullYear(),
					month:month,
					day:day,
				}
				
				return returnData
			},
			_timeMonthEndApi(timeDate) {
				var endDate=new Date(timeDate);
				var currentMonth=endDate.getMonth();
				
				var nextMonth=++currentMonth;
				var nextMonthFirstDay=new Date(endDate.getFullYear(),nextMonth,1);
				var oneDay=1000*60*60*24;
				var lastTime = new Date(nextMonthFirstDay-oneDay);
				var endMonth = parseInt(lastTime.getMonth()+1);
				var endDay = lastTime.getDate();
				if (endMonth < 10) {
					endMonth = '0' + endMonth
				}
				if (endDay < 10) {
					endDay = '0' + endDay
				}
				
				let timeDateEnd = endDate.getFullYear() + '-' + endMonth + '-' + endDay
				
				let returnData = {
					time_type:'end_time',
					time_int: Date.parse(timeDateEnd+' 00:00:00')/1000,
					time_date: timeDateEnd,
					year:endDate.getFullYear(),
					month:endMonth,
					day:endDay,
				}
				
				return returnData
			},
			//	2020-08-01
			getweek(date) {
				let weeks = [];
				let dates = this.getDates(date);
				// let len = Math.ceil(dates.weeks.length / 7);
				// for (let i = 0; i < len; i++) {
					// weeks.push(dates.weeks.slice(i * 7, 7 + (i * 7)));
				// }
				// dates.weeks = weeks
				return dates;
			},
			getDates(date) {
				let dates = {
					year: date.Year,
					month: date.Month,
					firstDay: new Date(date.Year, date.Month, 1).getDay(),
					lastDay: new Date(date.Year, date.Month + 1, 0).getDay(),
					endDate: new Date(date.Year, date.Month + 1, 0).getDate(),
					weeks: []
				}
				
				//计算上月日期
				for (let i = dates.firstDay; i > 0; i--) {
					let dateinfo = {};
					dateinfo.date = new Date(date.Year, date.Month, -i + 1).getDate();
					dateinfo.isClick = false;
					dates.weeks.push(dateinfo);
				}
				//计算本月日期
				for (let i = 1; i <= new Date(date.Year, date.Month + 1, 0).getDate(); i++) {
					let nowisClick = true;
					let dateinfo = {};
					dateinfo.date = i;
					if (this.dateType == 'dateCustom') {
						nowisClick = false;
						if (this.dateCustom[dates.year] && this.dateCustom[dates.year][dates.month]) {
							for (let j = 0; j < this.dateCustom[dates.year][dates.month].length; j++) {
								if (this.dateCustom[dates.year][dates.month][j] == i) {
									nowisClick = true;
								}
							}
						}
					}
					dateinfo.isClick = nowisClick;
					dates.weeks.push(dateinfo);
				}
				//计算下月日期
				let len = 7 - dates.lastDay;
				if ((42 - dates.weeks.length) >= 7) {
					len += 7;
				}
				for (let i = 1; i < len; i++) {
					let dateinfo = {};
					dateinfo.date = i;
					dateinfo.isClick = false;
					dates.weeks.push(dateinfo);
				}
				return dates;
			
			},
			
		}
			
	}
</script>
	
````

## 更新日志

* 2020年09月16日 v1.0.0
    *  增加自定义日历【注：可随意左右滑动】
	*  【亲测可用】

