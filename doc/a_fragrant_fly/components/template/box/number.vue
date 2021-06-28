<template>

		<view class="tui tui-flex uni-numbox" style="align-items: center;">
			<text class="iconfont  color-ju" :class="{'uni-numbox-disabled': disableSubtract}"  @click="_calcValue('subtract')">&#xe756;</text>
			<view class="tui-center" style="width: 50upx;">{{inputValue}}</view>
			<text class="iconfont  color-ju"    :class="{'uni-numbox-disabled': disableAdd}" @click="_calcValue('add')">&#xe600;</text>
		</view>
</template>
<script>
	// import uniIcon from '../icon/icon.vue';
	export default {
		name: 'uni-number-box',

		props: {
			other:{},
			item:{},
			value: {
				type: Number,
				default: 0
			},
			min: {
				type: Number,
				default: -Infinity
			},
			max: {
				type: Number,
				default: Infinity
			},
			step: {
				type: Number,
				default: 1
			},
			disabled: {
				type: Boolean,
				default: false
			}
		},
		data() {
			return {
				inputValue: this.value
			}
		},
		computed: {
			disableSubtract() {
				return this.value <= this.min
			},
			disableAdd() {
				return this.value >= this.max
			}
		},
		watch: {
			value(val) {
				this.inputValue = val;
			},
			inputValue(val) {
				// console.log(this.item)
				this.$emit('change',{value:val,item:this.item,other:this.other});
			}
		},
		methods: {
			_calcValue(type) {
				const scale = this._getDecimalScale();
				let value = this.inputValue * scale;
				let step = this.step * scale;

				if (type === 'subtract') {
					value -= step
				} else if (type === 'add') {
					value += step
				}
				if (value < this.min || value > this.max) {
					return
				}
				this.inputValue = value / scale;
			},
			_getDecimalScale() {
				let scale = 1;
				// 浮点型
				if (~~this.step !== this.step) {
					scale = Math.pow(10, (this.step + '').split('.')[1].length);
				}
				return scale;
			}
		}
	}
</script>
<style>
	.uni-numbox {
		display: flex;
		flex-direction: row;
		justify-content: flex-start;
		font-size:1em ;
		height: 70upx;
	}
	.uni-numbox icon{
		color:#fff;
	}

	.uni-numbox-disabled {
		color: #c0c0c0;
	}
</style>
