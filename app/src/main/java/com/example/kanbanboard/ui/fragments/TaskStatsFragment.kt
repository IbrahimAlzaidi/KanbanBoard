package com.example.kanbanboard.ui.fragments

import android.view.LayoutInflater
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.example.kanbanboard.R
import com.example.kanbanboard.data.DbHelper
import com.example.kanbanboard.data.DbSchema
import com.example.kanbanboard.databinding.FragmentTaskBinding
import com.github.aachartmodel.aainfographics.aachartcreator.*


class TaskStatsFragment:BaseFragment<FragmentTaskBinding>() {

    val listStatus = arrayListOf(
        "Bar" ,
        "Columnar" ,
        "Line",
        "Scatter" ,
    )
    val listStatus2 = arrayListOf(
        "Bar" ,
        "Columnar" ,
        "Line",
        "Scatter" ,
    )

    lateinit var db : DbHelper
    override val LOG_TAG: String = "TaskStats Fragment"
    override val bindingInflater: (LayoutInflater) -> FragmentTaskBinding
        get() = FragmentTaskBinding::inflate

    override fun setup() {
        db=DbHelper(requireActivity().applicationContext)
//        db.filterTaskByStatsChart("Done",DbSchema.TASK_STATS,"stats")
//        db.filterTaskByStatsChart("Done",DbSchema.TASK_TYPE,"task_type")
    }

    override fun addCallBack() {
        chart1DataSet(AAChartType.Bar)
        chart2DataSet(AAChartType.Bar)
        dataSpinner()
    }
    private fun dataSpinner(){
        val spinnerAdapter =
            context?.let { ArrayAdapter(it,R.layout.support_simple_spinner_dropdown_item,listStatus) }
        val spinnerAdapter2 =
            context?.let { ArrayAdapter(it,R.layout.support_simple_spinner_dropdown_item,listStatus2) }
        binding?.firstSpinner?.apply {
            adapter = spinnerAdapter
            onClickSpinner()
        }
        binding?.secondSpinner?.apply {
            adapter = spinnerAdapter2
            onClickSpinner()
        }
    }

    private fun onClickSpinner() {

        binding?.firstSpinner?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, poisition: Int, p3: Long) {
                when(listStatus[poisition]){
                    listStatus[0] -> {chart1DataSet(AAChartType.Bar)}//yes
                    listStatus[1] -> {chart1DataSet(AAChartType.Columnrange)}//yes
                    listStatus[2] -> {chart1DataSet(AAChartType.Line)}
                    listStatus[3] -> {chart1DataSet(AAChartType.Scatter)}//yes
                }
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {
            }
        }

        binding?.secondSpinner?.onItemSelectedListener = object :AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
                when (listStatus2[position]){
                    listStatus2[0] -> {}
                    listStatus2[1] -> {}
                    listStatus2[2] -> {}
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }
        }
    }

    private fun chart1DataSet(typeOfChart : AAChartType){
        val aaChartView = binding?.paiChart
        val aaChartModel : AAChartModel = AAChartModel()
            .chartType(typeOfChart)
            .backgroundColor("#FFFFFFFF")
            .dataLabelsEnabled(false)//show number of array on the items
            .legendEnabled(true)//show the filter point
            .polar(true)//make any chart circular
            .title("Status")//display title in the chart header
            .zoomType(AAChartZoomType.XY)//like is name
            .tooltipEnabled(true)//when user click in any point in chart is shown square having details for this points
            .xAxisGridLineWidth(1f)//the circular grid line in the chart
            .xAxisLabelsEnabled(false)//display the xAxis text "Values of #Categories"
            .yAxisLabelsEnabled(false)//display the yAxis text "Values"
            .categories(arrayOf("Jan","Feb","Mar","Apr","May","Jun","Jul","Aug"))
            .series(arrayOf(
                AASeriesElement()
                    .name("Done")
                    .color("#689F38")
                    .enableMouseTracking(true)
                    .data(db.filterTaskByStatsChart("Done",DbSchema.TASK_STATS,"stats").toTypedArray()),
                AASeriesElement()
                    .name("in progress")
                    .color("#FFD10F")
                    .enableMouseTracking(true)
                    .data(db.filterTaskByStatsChart("in progress",DbSchema.TASK_STATS,"stats").toTypedArray()),
                AASeriesElement()
                    .name("in backlog")
                    .color("#c62828")
                    .data(db.filterTaskByStatsChart("in backlog",DbSchema.TASK_STATS,"stats").toTypedArray())
                    .enableMouseTracking(true),
            )
            )
            .animationDuration(3000)
            .animationType(AAChartAnimationType.Bounce)
        aaChartView?.aa_drawChartWithChartModel(aaChartModel)
        aaChartView?.aa_updateChartWithOptions(aaChartModel,true)

    }
    private fun chart2DataSet(typeOfChart : AAChartType){
        val aaChartView = binding?.paiChartCard2
        val aaChartModel : AAChartModel = AAChartModel()
            .chartType(typeOfChart)
            .backgroundColor("#FFFFFFFF")
            .dataLabelsEnabled(false)//show number of array on the items
            .legendEnabled(true)//show the filter point
            .polar(true)//make any chart circular
            .title("Status")//display title in the chart header
            .zoomType(AAChartZoomType.XY)//like is name
            .tooltipEnabled(true)//when user click in any point in chart is shown square having details for this points
            .xAxisGridLineWidth(1f)//the circular grid line in the chart
            .xAxisLabelsEnabled(true)//display the xAxis text "Values of #Categories"
            .yAxisLabelsEnabled(false)//display the yAxis text "Values"
            .categories(arrayOf("Jan","Feb","Mar","Apr","May","Jun","Jul","Aug"))
            .series(arrayOf(
                AASeriesElement()
                    .name("Design")
                    .color("#689F38")
                    .enableMouseTracking(true)
                    .data(db.filterTaskByStatsChart("design",DbSchema.TASK_TYPE,"task_type").toTypedArray()),
                AASeriesElement()
                    .name("Implement")
                    .color("#FFD10F")
                    .enableMouseTracking(true)
                    .data(db.filterTaskByStatsChart("implement",DbSchema.TASK_TYPE,"task_type").toTypedArray()),
                AASeriesElement()
                    .name("Debug")
                    .color("#c62828")
                    .data(db.filterTaskByStatsChart("debug",DbSchema.TASK_TYPE,"task_type").toTypedArray())
                    .enableMouseTracking(true),
                AASeriesElement()
                    .name("Review")
                    .color("#c62828")
                    .data(db.filterTaskByStatsChart("review",DbSchema.TASK_TYPE,"task_type").toTypedArray())
                    .enableMouseTracking(true),
            )
            )
            .animationDuration(3000)
            .animationType(AAChartAnimationType.Bounce)
        aaChartView?.aa_drawChartWithChartModel(aaChartModel)
        aaChartView?.aa_updateChartWithOptions(aaChartModel,true)

    }

}
