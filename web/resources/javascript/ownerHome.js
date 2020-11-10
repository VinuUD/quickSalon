function checkAddress(checkbox)
{
    if (checkbox.checked)
    {
        document.getElementById("bg-manager-home").style.marginLeft="300px";
        document.getElementById("welcome-message").style.marginLeft="300px";
        document.getElementById("welcome-message").style.display = 'none';
        document.getElementById("profile-picture").style.display = 'none';
    }
    else
    {
        document.getElementById("bg-manager-home").style.marginLeft="0px";
        document.getElementById("welcome-message").style.marginLeft="0px";
        setTimeout(function(){document.getElementById("welcome-message").style.display = 'initial';}, 275);
        setTimeout(function(){document.getElementById("profile-picture").style.display = 'initial';}, 275);
    }
}
