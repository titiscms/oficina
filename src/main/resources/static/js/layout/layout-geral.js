$('#modalPesquisaVeiculos').on('shown.bs.modal', function (e) {
	
	var input = $('.js-input');
	var label = $('.js-label');
	var isInputVazio = input.on('input').val();
	
	input.on('focusin', function() {
		
		if (isInputVazio != '') {
			label.removeAttr('hidden');
		} else {
			label.attr('hidden');
		}
		
	});
	
});
