<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Insert title here</title>
	</head>
	<body>
		<form class="form-horizontal">
			<fieldset>
				<legend >Edit Foo</legend>
				<div class="control-group">
					<label class="control-label" for="input01">Text input</label>
					<div class="controls">
						<input type="text" class="input-xlarge" id="input01">
						<p class="help-block">In addition to freeform text, any HTML5 text-based input appears like so.</p>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="optionsCheckbox">Checkbox</label>
					<div class="controls">
						<label class="checkbox">
							<input type="checkbox" id="optionsCheckbox" value="option1">
							Option one is this and that&mdash;be sure to include why it's great
						</label>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="select01">Select list</label>
					<div class="controls">
						<select id="select01">
							<option>something</option>
							<option>2</option>
							<option>3</option>
							<option>4</option>
							<option>5</option>
						</select>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="multiSelect">Multicon-select</label>
					<div class="controls">
						<select multiple="multiple" id="multiSelect">
							<option>1</option>
							<option>2</option>
							<option>3</option>
							<option>4</option>
							<option>5</option>
						</select>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="textarea">Textarea</label>
					<div class="controls">
						<textarea class="input-xlarge" id="textarea" rows="3"></textarea>
					</div>
				</div>
				<div class="form-actions">
					<button type="submit" class="btn btn-primary">Save changes</button>
					<a class="btn" href="../">Cancel</a>
				</div>
			</fieldset>
		</form>	
	</body>
</html>
