var margin = {top: 16, right: 30, bottom: 85, left: 90};
var leftPadding = 100;
var innerPadding = 20;
var width = 960;
var height = 500;
var barPadding = 0.1;

var xColumn = "이름";
var yColumn = "득표율";
var colorColumn = "이름";

var innerWidth = width - margin.left - margin.right;
var innerHeight = height - margin.top - margin.bottom;

$(document).ready(function(){
	var formatPercent = d3.format(".0%")
	var x = d3.scale.ordinal().rangeRoundBands([leftPadding,innerWidth-innerPadding],barPadding);
	var y = d3.scale.linear().range([innerHeight-innerPadding, innerPadding]);
	var xAxis = d3.svg.axis().scale(x).orient("bottom");
	var yAxis = d3.svg.axis().scale(y).orient("left").tickFormat(formatPercent);
	var colorScale = d3.scale.category10();
	

	d3.json('',function(data){
		data = rawdata;
		data = d3.keys(rawdata)
				.map(function(key){
					return{
						name:data[key].name,
						vote:Number(data[key].vote)
					};
				});
		
		var sum = d3.sum(data,function(d){
			return d.vote;
		});
		
		x.domain(data.map(function(d){
			return d.name;
		}));
		y.domain([0,d3.max(data,function(d){
			return d.vote/sum;
		})]);
		
		colorScale.domain(data.map(function(d){
			return d[colorColumn];
		}));
		
		var svg = d3.select("#chart")
		.append("svg")
		.attr("width",width)
		.attr("height",height);

		var xg = svg.append("g")
				.attr("class","x axis")
				.attr("transform","translate(0, "+(innerHeight) + ")")
				.style({ 'stroke': 'black', 'fill': 'none', 'stroke-width': '1px'})
				.call(xAxis);
		
		var yg = svg.append("g")
				.attr("class","y axis")
				.attr("transform","translate("+(leftPadding)+","+innerPadding+")") // (
				.style({ 'stroke': 'black', 'fill': 'none', 'stroke-width': '1px'})
				.call(yAxis);

		
		var bars = svg.selectAll('rect').data(data);
		
		bars.enter()
		.append('rect')
		.attr('class','bar')
		.attr('x',function(d){
			return x(d.name);
		})
		.attr('width',x.rangeBand());
		
		bars.attr('y',height-innerPadding)
		.transition()
		.delay(function(d,i){
			return i*20;
		})
		.duration(800)
		.attr('y',function(d){
			return y(d.vote/sum)
		})
		.attr('height',function(d){
			return innerHeight - y(d.vote/sum);
		});
		
		bars.attr("fill",function(d,i){
			return colorScale(i);
		});
		
		
		svg.selectAll(".label")
        .data(data)
        .enter()
        .append("text")
        .attr("class", "label")
        .attr("text-anchor", "middle")
        .style("font-size","20px")
        .style("font-weight","bold")
        .attr("font-family", "sans-serif")
        .text( function(d) {  
        	return formatPercent(d.vote/sum);
        	
        } )
        .attr("x", function(d) { return x(d.name) + x.rangeBand()/2; })
        .attr("y", function(d) { return y(d.vote/sum); })
        .attr("dy", "0.9em")
	});

});