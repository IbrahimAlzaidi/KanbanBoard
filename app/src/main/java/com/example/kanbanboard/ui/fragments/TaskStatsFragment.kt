package com.example.kanbanboard.ui.fragments

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.widget.AdapterView
import com.example.kanbanboard.R
import com.example.kanbanboard.data.DbHelper
import com.example.kanbanboard.data.DbSchema
import com.example.kanbanboard.databinding.FragmentTaskBinding
import com.github.aachartmodel.aainfographics.aachartcreator.*
import android.widget.ArrayAdapter as ArrayAdapter1


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
    }

    override fun addCallBack() {
        chart1DataSet(AAChartType.Bar)
        chart2DataSet(AAChartType.Bar)
        dataSpinner()
    }
    @SuppressLint("ResourceType")
    private fun dataSpinner(){
        val spinnerAdapter = ArrayAdapter1(requireContext().applicationContext,R.layout.support_simple_spinner_dropdown_item,listStatus)
        val spinnerAdapter2 = ArrayAdapter1(requireContext().applicationContext,R.layout.support_simple_spinner_dropdown_item,listStatus2)

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
                    listStatus[0] -> {chart2DataSet(AAChartType.Bar)}//yes
                    listStatus[1] -> {chart2DataSet(AAChartType.Columnrange)}//yes
                    listStatus[2] -> {chart2DataSet(AAChartType.Line)}
                    listStatus[3] -> {chart2DataSet(AAChartType.Scatter)}//yes
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
            .title("")//display title in the chart header
            .zoomType(AAChartZoomType.XY)//like is name
            .tooltipEnabled(true)//when user click in any point in chart is shown square having details for this points
            .xAxisGridLineWidth(1f)//the circular grid line in the chart
            .xAxisLabelsEnabled(false)//display the xAxis text "Values of #Categories"
            .yAxisLabelsEnabled(false)//display the yAxis text "Values"
            .categories(arrayOf("Tasks Status :","Tasks Status :","Tasks Status :","Tasks Status :"))
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

    }//this function provide chart depending on Task Status numbers.
    private fun chart2DataSet(typeOfChart : AAChartType){
        val aaChartView = binding?.paiChartCard2
        val aaChartModel : AAChartModel = AAChartModel()
            .chartType(typeOfChart)
            .backgroundColor("#FFFFFFFF")
            .dataLabelsEnabled(false)//show number of array on the items
            .legendEnabled(true)//show the filter point
            .polar(true)//make any chart circular
            .title("")//display title in the chart header
            .zoomType(AAChartZoomType.XY)//like is name
            .tooltipEnabled(true)//when user click in any point in chart is shown square having details for this points
            .xAxisGridLineWidth(1f)//the circular grid line in the chart
            .xAxisLabelsEnabled(false)//display the xAxis text "Values of #Categories"
            .yAxisLabelsEnabled(false)//display the yAxis text "Values"
            .categories(arrayOf("Tasks Type","Tasks Type","Tasks Type","Tasks Type"))
            .series(arrayOf(
                AASeriesElement()
                    .name("Design")
                    .color("#3f37c9")
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
                    .color("#689F38")
                    .data(db.filterTaskByStatsChart("review",DbSchema.TASK_TYPE,"task_type").toTypedArray())
                    .enableMouseTracking(true),
            )   )
            .animationDuration(3000)
            .animationType(AAChartAnimationType.Bounce)
        aaChartView?.aa_drawChartWithChartModel(aaChartModel)
        aaChartView?.aa_updateChartWithOptions(aaChartModel,true)

    }//this function provide chart depending on Task Type numbers.

    override fun onStart() {
        super.onStart()
        binding?.newTaskNumber?.text = db.filterTaskByStatsChart("in backlog",DbSchema.TASK_STATS,"stats").toString().subSequence(1,2)
        binding?.finishedTaskNumber?.text = db.filterTaskByStatsChart("Done",DbSchema.TASK_STATS,"stats").toString().subSequence(1,2)
    }
    /*this function using to make the textView take last update adding in
    the DataBase when the user switch between the fragments.*/
}
