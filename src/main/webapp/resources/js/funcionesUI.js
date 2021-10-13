function handleLoginRequest(xhr, status, args) {
    if(args.validationFailed || !args.loggedIn) {
        PF('dialogo').jq.effect("shake", {times:5}, 100);
    }
    else {
        PF('dialogo').hide();
        $('#loginLink').fadeOut();
    }
}

function clickCarro(){
    if(PF('dialogoCarro').isVisible()){
        PF('dialogoCarro').hide();
    }else{
        PF('dialogoCarro').show();
    }
}
