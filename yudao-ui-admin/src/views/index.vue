<template>
  <div class="dashboard-editor-container">

    <panel-group @handleSetLineChartData="handleSetLineChartData" />

    <el-row style="background:#fff;padding:16px 16px 0;margin-bottom:32px;">
      <line-chart :chart-data="lineChartData" />
    </el-row>

    <el-row :gutter="32">
      <el-col :xs="24" :sm="24" :lg="8">
        <!-- <div class="chart-wrapper">
          <raddar-chart />
        </div> -->
        <div class="chart-wrapper">
          <h2>总计支出</h2>
          <pie-chart :PieData="zhichu" />
        </div>
      </el-col>
      <el-col :xs="24" :sm="24" :lg="8">
        <div class="chart-wrapper">
          <h2>预算</h2>
          <pie-chart :PieData="yuSuan" />
        </div>
      </el-col>
      <el-col :xs="24" :sm="24" :lg="8">
        <h1>支出</h1>
        <div class="chart-wrapper">
          <bar-chart :barDate="LineData" />
        </div>
      </el-col>
    </el-row>


  </div>
</template>

<script>
import PanelGroup from './dashboard/PanelGroup'
import LineChart from './dashboard/LineChart'
import RaddarChart from './dashboard/RaddarChart'
import PieChart from './dashboard/PieChart'
import BarChart from './dashboard/BarChart'
import { getCategoryAndAmount, getCategoryAndDate } from "../api/Observer"
const lineChartData = {
  newVisitis: {
    expectedData: [100, 120, 161, 134, 105, 160, 165],
    actualData: [120, 82, 91, 154, 162, 140, 145]
  },
  messages: {
    expectedData: [200, 192, 120, 144, 160, 130, 140],
    actualData: [180, 160, 151, 106, 145, 150, 130]
  },
  purchases: {
    expectedData: [80, 100, 121, 104, 105, 90, 100],
    actualData: [120, 90, 100, 138, 142, 130, 130]
  },
  shoppings: {
    expectedData: [130, 140, 141, 142, 145, 150, 160],
    actualData: [120, 82, 91, 154, 162, 140, 130]
  }
}

export default {
  name: 'Index',
  components: {
    PanelGroup,
    LineChart,
    RaddarChart,
    PieChart,
    BarChart
  },
  data() {
    return {
      lineChartData: lineChartData.newVisitis,
      yuSuan: [],
      LineData: {
        data:[],
        series:[]
      },
      zhichu:[]
    }
  },
  created() {
    this.CategoryAndAmountPie()
    this.categoryDate()
  },
  methods: {
    categoryDate() {
      getCategoryAndDate().then(res => {
        // {
        //   name: 'pageA',
        //   type: 'bar',
        //   stack: 'vistors',
        //   barWidth: '60%',
        //   data: [79, 52, 200, 334, 390, 330, 220],
        //   animationDuration
        // }
        console.log(res)
        res.forEach(e => {
          this.LineData.data.push(`${e.budgetDate[0]}-${e.budgetDate[1]}-${e.budgetDate[2]}`)
          this.LineData.series.push({
            name: e.category,
            type: 'bar',
            stack: 'vistors',
            barWidth: '60%',
            data: [e.spentAmount],
            
          })
        })
      })
    },
    CategoryAndAmountPie() {
      getCategoryAndAmount().then(res => {
        console.log(res)
        res.forEach(element => {
          this.zhichu.push({
            name: element.category,
            value: element.spentAmount||0
          })
          this.yuSuan.push({
            name: element.category,
            value: element.amount||0
          })
        });
        console.log(this.zhichu)
      })
    },
    handleSetLineChartData(type) {
      this.lineChartData = lineChartData[type]
    }
  }
}
</script>

<style lang="scss" scoped>
.dashboard-editor-container {
  padding: 32px;
  background-color: rgb(240, 242, 245);
  position: relative;

  .chart-wrapper {
    background: #fff;
    padding: 16px 16px 0;
    margin-bottom: 32px;
  }
}

@media (max-width:1024px) {
  .chart-wrapper {
    padding: 8px;
  }
}
</style>
