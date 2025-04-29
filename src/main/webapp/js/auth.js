$(document).ready(() => {
    function cifrar(message, key) {
        return CryptoJS.DES.encrypt(message, CryptoJS.enc.Utf8.parse(key), {
            mode: CryptoJS.mode.ECB,
            padding: CryptoJS.pad.Pkcs7
        }).toString();
    }

    const secretKey = "lafedecuto";
    $("#btnLogin").on("click", () => {
        const logi = $("#log").val();
        const pass = $("#pass").val();
        const encryptedPass = cifrar(pass, secretKey);
        console.log(encryptedPass);
        $.ajax({
            url: 'loginuser',
            data: {log: logi, pass: encryptedPass},
            method: 'POST',
            success: function (data, textStatus, jqXHR) {
                console.log(data);
                if (data.result === "ok") {
                    window.location.href = "principal.html";
                } else {
                    window.location.href = "404.html";
                }
            },
            error: function (txtStatus, errorThrown) {
                console.log("Error en la solicitud: " + txtStatus, errorThrown);
            }
        });
    });
});
