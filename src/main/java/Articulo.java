
public class Articulo {

    String[] MS = {"el", "ese"};
    String[] articulosMP = {"los", "esos"};
    String[] articulosFS = {"la", "esa"};
    String[] articulosFP = {"las", "esas"};
    String articulo;
    String sustantivo;
    boolean conector;
    String[] linea;

    public Articulo() {
    }

    public boolean ValidaArticulo(String articulo, String sustantivo, String verbo, String[] Oracion, boolean conector) {
        this.conector = conector;
        this.linea = Oracion;
        this.articulo = articulo;
        this.sustantivo = sustantivo;
        //Variable del tipo de articulo
        String ar = "NA";

        //Variable de control (Indicador si encontró el articulo)
        boolean valido = false;

        //Revisa si ya se encontro previamente el articulo
        if (!valido) {
            //Revisión en los articulos masculinos plural
            for (int i = 0; i < articulosMP.length; i++) {
                if (articulosMP[i].equals(articulo)) {
                    //Indica el tipo de articulo
                    ar = "mp";
                    //Indica que ha encontrado el articulo
                    valido = true;
                    break;
                }
            }
        }

        //Revisa si ya se encontro previamente el articulo
        if (!valido) {
            //Revisión en los articulos femeninos singular
            for (String art : articulosFS) {
                if (art.equals(articulo) || art.toUpperCase().equals(articulo) || art.toLowerCase().equals(articulo)) {
                    //Indica el tipo de articulo
                    ar = "fs";
                    //Indica que ha encontrado el articulo
                    valido = true;
                    break;
                }
            }
        }

        //Revisión en los articulos masculinos singular
        if (!valido) {
            for (String art : MS) {
                if (art.equals(articulo) || art.toUpperCase().equals(articulo) || art.toLowerCase().equals(articulo)) {
                    //Indica el tipo de articulo
                    ar = "ms";
                    //Indica que ha encontrado el articulo
                    valido = true;
                    break;
                }
            }
        }

        //Revisa si ya se encontro previamente el articulo
        if (!valido) {
            //Revisión en los articulos femeninos plural
            for (String art : articulosFP) {
                if (art.equals(articulo) || art.toUpperCase().equals(articulo) || art.toLowerCase().equals(articulo)) {
                    //Indica el tipo de articulo
                    ar = "fp";
                    //Indica que ha encontrado el articulo
                    valido = true;
                    break;
                }
            }
        }

        //Revisa si ya se encontro previamente el articulo
        if (valido) {
            //Pasa al siguiente paso de la evaluacíon, indicando true, en caso de que sea valido, y false en caso de que sea invalido
            //Envía datos como el tipodeArticulo, Articulo, Sustantivo y el verbo
            valido = this.validarArticulo(ar, articulo, sustantivo, verbo);
            //Revisa el resultado anterior
            if (valido) {
                String resultado = "";
                //Concatena (Junta) toda la oracion e indica que es correcta
                for (String palabra : Oracion) {
                    resultado += palabra + " ";
                }
                System.out.println("La oración (" + resultado.trim() + ") es correcta!");
            }
            //Si no es correcta, no hace nada (En cada metodo indica el error)
        } else {
            //Si no encontro el articulo, hace esto
            System.out.println("No se encontró ningún artículo válido con *" + articulo + "*");
            System.out.println("Intente usar uno de los siguientes artículos: ");
            //Sugiere los articulos que pueden ser usados
            for (String a : MS) {
                System.out.println(a + " + ...");
            }
            for (String a : articulosMP) {
                System.out.println(a + " + ...");
            }
            for (String a : articulosFS) {
                System.out.println(a + " + ...");
            }
            for (String a : articulosFP) {
                System.out.println(a + " + ...");
            }
        }
        return valido;
    }

    public boolean validarArticulo(String tipo, String articulo, String sustantivo, String verbo) {
        //Variable de control (Indicador si encontró el sustantivo)
        boolean validado = false;
        String tipoVerbo = "NA";
        /*
        Diccionario de palabras
        M = Masculino
        F = Femenino
        S = Singular
        P = Plural
         */
        String[] MS = {"niño", "perro", "carro", "bote"};
        String[] MP = {"niños", "perros", "carros", "botes"};
        String[] FS = {"niña", "casa", "muñeca", "lancha"};
        String[] FP = {"niñas", "casas", "muñecas", "lanchas"};

        //Usa el tipo de articulo para definir que hara a continuanción
        switch (tipo) {
            //"ms" se refiere al tipo de articulo que se esta manejando
            case "ms":
                //Busca en el diccionario de sustantivos
                for (int i = 0; i < MS.length; i++) {
                    if (MS[i].equals(sustantivo)) {
                        //Indica que lo encontro
                        validado = true;
                        //Indica el tipo de verbo a evaluar para el siguiente paso
                        tipoVerbo = "singular";
                        //Una vez encontrado, no es necesario continuar, por lo que sale del ciclo
                        break;
                    }
                }

                //Si no lo encontro...
                if (!validado) {
                    //Revisa la terminación de la palabra para verificar si es un verbo valido
                    String fin = sustantivo.substring(sustantivo.length() - 1);
                    String fin2 = sustantivo.substring(sustantivo.length() - 2);
                    //Revisa las terminaciones
                    if (fin.equals("o") || fin2.equals("on") || fin2.equals("ón")) {
                        //Si tiene la terminación correcta, posiblemente es te correcto, por lo que no indica las opciones posibles.
                        System.out.println("El sustantivo *" + sustantivo + "* no esta en el diccionario de la aplicación");
                    } else {
                        //Si la terminación no es correcta, entonces indica las opciones que se encuentran en el diccionario al momento de ejecución
                        System.out.println("El artículo *" + articulo + "* no conjuga con el sustantivo *" + sustantivo + "*");
                        System.out.println("Intente usar uno de los siguientes sustantivos: ");
                        //Imprime todas las sugerencias
                        for (String s : MS) {
                            System.out.println(articulo + " + *" + s + "*...");
                        }
                    }
                }
                break;
            case "mp":
                for (String m : MP) {
                    if (m.equals(sustantivo) || m.toLowerCase().equals(sustantivo) || m.toUpperCase().equals(sustantivo)) {
                        validado = true;
                        tipoVerbo = "plural";
                        break;
                    }
                }
                if (!validado) {

                    System.out.println("El artículo *" + articulo + "* no conjuga con el sustantivo *" + sustantivo + "*");
                    System.out.println("Intente usar uno de los siguientes sustantivos: ");
                    for (String s : MP) {
                        System.out.println(articulo + " + *" + s + "*...");
                    }

                }
                break;
            case "fs":
                for (String f : FS) {
                    if (f.equals(sustantivo) || f.toLowerCase().equals(sustantivo) || f.toUpperCase().equals(sustantivo)) {
                        tipoVerbo = "singular";
                        validado = true;
                        break;
                    }
                }
                if (!validado) {
                    String fin = sustantivo.substring(sustantivo.length() - 1);
                    String fin2 = sustantivo.substring(sustantivo.length() - 2);
                    if (fin.equals("a") || fin2.equals("on") || fin2.equals("ón")) {
                        System.out.println("El sustantivo *" + sustantivo + "* no esta en el diccionario de la aplicación");
                    } else {
                        System.out.println("El artículo *" + articulo + "* no conjuga con el sustantivo *" + sustantivo + "*");
                        System.out.println("Intente usar uno de los siguientes sustantivos: ");
                        for (String s : FS) {
                            System.out.println(articulo + " + *" + s + "*...");
                        }
                    }

                }
                break;
            case "fp":
                for (String f : FP) {
                    if (f.equals(sustantivo) || f.toLowerCase().equals(sustantivo) || f.toUpperCase().equals(sustantivo)) {
                        tipoVerbo = "plural";
                        validado = true;
                        break;
                    }
                }
                if (!validado) {
                    String fin = sustantivo.substring(sustantivo.length() - 2);
                    if (fin.equals("as")) {
                        System.out.println("El sustantivo *" + sustantivo + "* no esta en el diccionario de la aplicación");
                    } else {
                        System.out.println("El artículo *" + articulo + "* no conjuga con el sustantivo *" + sustantivo + "*");
                        System.out.println("Intente usar uno de los siguientes sustantivos: ");
                        for (String s : FP) {
                            System.out.println(articulo + " + *" + s + "*...");
                        }
                    }

                }
                break;
            //Opción en caso de que se envie un tipo de dato no valido
            default:
                System.out.println("Tipo no valido " + tipo);
                break;

        }
        //Verifica si se encontro el sustantivo dentro de la lista
        if (validado) {
            //En caso de que se haya encontrado pasa a la fase final en donde evalua el verbo de acuerdo al tipo de verbo
            //Si todo es correcto, regresa el valor de true.
            return this.validarVerbo(tipoVerbo, articulo, sustantivo, verbo);
        } else {
            //Si no se encontro el sustantivo, termina el proceso indicando false
            return validado;
        }

    }

    //Diccionario de verbos
    String[] verboSingular = {"come", "corre", "camina", "lee", "juega", "divierte"};
    String[] verboPlural = {"comen", "corren", "caminan", "leen", "juegan", "divierten"};

    public boolean validarVerbo(String tipoVerbo, String articulo, String sustantivo, String verbo) {

        //Variable de control (Indicador si encontró el articulo)
        boolean valido = false;
        boolean conectorValido = true;

        //Lee y verifica solo la primera letra del tipo de verbo
        switch (tipoVerbo.substring(0, 1)) {
            case "s":
                //Lee la lista de verbos
                for (String v : verboSingular) {
                    //Compara si existe el verbo indicado en una de sus tres variables (Primera letra mayuscula, todo munisculas, todo mayusculas)
                    if (v.equals(verbo) || v.toLowerCase().equals(verbo) || v.toUpperCase().equals(verbo)) {
                        //Indica que el verbo es valido
                        valido = true;
                        if (this.conector) {
                            conectorValido = this.validarVerboFinal("singular");
                        }
                        break;
                    }
                }
                //Verifica si el verbo no es valido
                if (!valido) {
                    //Identifica la ultima letra
                    String fin = verbo.substring(verbo.length() - 1);
                    //Revisa la terminación del verbo
                    if (fin.equals("a") || fin.equals("e")) {
                        //Si corresponde es posible que este correcto y no se encuentre en el diccionario
                        System.out.println("La acción *" + verbo + "* no está en el diccionario de la aplicación");
                    } else {
                        //Si no tuvo esa terminación, es muy probable que sea una conjuganción incorrecta
                        //Se dan sugerencias
                        System.out.println("La acción *" + verbo + "* no está conjugado correctamente en *" + tipoVerbo + "*");
                        System.out.println("Intente usar uno de los siguientes verbos: ");
                        for (String s : verboSingular) {
                            System.out.println(articulo + " " + sustantivo + " *" + s + "*...");
                        }
                    }
                }
                break;
            case "p":
                for (String v : verboPlural) {
                    if (v.equals(verbo) || v.toLowerCase().equals(verbo) || v.toUpperCase().equals(verbo)) {
                        //System.out.println(verbo + " (válido)");
                        valido = true;
                        if (this.conector) {
                            conectorValido = this.validarVerboFinal("plural");
                        }
                        break;
                    }
                }
                if (!valido) {
                    String fin = verbo.substring(verbo.length() - 2);
                    if (fin.equals("an") || fin.equals("en")) {
                        System.out.println("La acción *" + verbo + "* no está en el diccionario de la aplicación");
                    } else {
                        System.out.println("La acción *" + verbo + "* no está conjugado correctamente en *" + tipoVerbo + "*");
                        System.out.println("Intente usar uno de los siguientes verbos: ");
                        for (String s : verboPlural) {
                            System.out.println(articulo + " " + sustantivo + " *" + s + "*...");
                        }
                    }
                }
                break;
            default:
                break;
        }
        //Finalmente regresa true si es valido el verbo o false si no es valido
        return valido && conectorValido;
    }

    public boolean validarVerboFinal(String tipoVerbo) {
        boolean valido = false;
        String verbo = this.linea[linea.length - 1];
        switch (tipoVerbo.substring(0, 1)) {
            case "s":
                for (String v : verboSingular) {
                    //Compara si existe el verbo indicado en una de sus tres variables (Primera letra mayuscula, todo munisculas, todo mayusculas)
                    if (v.equals(verbo)) {
                        //Indica que el verbo es valido
                        valido = true;
                        break;
                    }
                }
                if (!valido) {
                    //Identifica la ultima letra
                    String fin = verbo.substring(verbo.length() - 1);
                    //Revisa la terminación del verbo
                    if (fin.equals("a") || fin.equals("e")) {
                        //Si corresponde es posible que este correcto y no se encuentre en el diccionario
                        System.out.println("La acción *" + verbo + "* no está en el diccionario de la aplicación");
                    } else {
                        //Si no tuvo esa terminación, es muy probable que sea una conjuganción incorrecta
                        //Se dan sugerencias
                        System.out.println("La acción *" + verbo + "* no está conjugado correctamente en *" + tipoVerbo + "*");
                        System.out.println("Intente usar uno de los siguientes verbos: ");
                        for (String s : verboSingular) {
                            System.out.println(articulo + " " + sustantivo + " *" + s + "*...");
                        }
                    }
                }
                break;
            case "p":
                for (String v : verboPlural) {
                    //Compara si existe el verbo indicado)
                    if (v.equals(verbo)) {
                        //Indica que el verbo es valido
                        valido = true;
                        break;
                    }
                }
                if (!valido) {
                    String fin = verbo.substring(verbo.length() - 2);
                    if (fin.equals("an") || fin.equals("en")) {
                        System.out.println("La acción *" + verbo + "* no está en el diccionario de la aplicación");
                    } else {
                        System.out.println("La acción *" + verbo + "* no está conjugado correctamente en *" + tipoVerbo + "*");
                        System.out.println("Intente usar uno de los siguientes verbos: ");
                        for (String s : verboPlural) {
                            System.out.println(this.articulo + " " + this.sustantivo + " *" + s + "*...");
                        }
                    }
                }
                break;
            default:
                break;
        }
        return valido;
    }
}
