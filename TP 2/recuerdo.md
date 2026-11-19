# recuerdo.md — memoria de trabajo (HTML de defensa TP 2)

Última actualización: 18/11/2026 ~00:40

## Tarea
HTML (artifact privado en claude.ai; luego el usuario pide PDF) con lo que todos deben saber para la **defensa oral del TP 2 (vie 20/11)**. Corto, no verboso, solo lo no trivial/no obvio en el código. Prioridad: prepararse para la defensa; errores/descuidos solo si están **verificados**. Terminar antes de las 2:00.

## Reglas del usuario
- TP1 vs TP2 contradictorio → vale TP1. Lo que TP1 pedía y TP2 no, ya no es obligatorio.
- TP2 ya entregado: no se modifica; se defiende lo hecho.
- `AGENTS.md` (carpeta padre) es de otro contexto: NO usarlo ni editarlo (lo toqué una vez y lo revertí).
- Lo pesado → `run_in_background`. Docker: no se usa. Borrado: `TP 2/repaso_tp2.html`.
- Temporales en el scratchpad; borrar al terminar. Usar ambas grabaciones (ogg y mp4) y contrastar.

## Archivos
- Código: `TP 2/src/main/java/aed/tp2/` (`AirMiles`, `recursos/`: `ABB`, `HeapHandle`, `Trie`, `Usuario`, `ElementoRanking`, `NodoHandle`, `Promocion`, `Conjunto`). Plantilla sin usar: `Handle.java` (interfaz), `Iterador.java`.
- `TP 2/justificacion.pdf` (equipo "Los4Bugueados").
- Enunciados: `Recursos/Laboratorio/Laboratorio - TP 1.pdf` y `... TP 2.pdf`. Teóricas: `Recursos/Teórica/` (T9 ABB/AVL, T10 heaps, T11 tries).
- Grabaciones: `../Reunion/` (12 `.ogg` celular, 4 `.mp4` OBS).
- Scratchpad: `C:\Users\ALEJAN~1\AppData\Local\Temp\claude\058ec4a0703def7d\c5a6b2d0-e026-4824-8668-5b64b3b319c9\scratchpad\`
  - `batch.py` (faster-whisper large-v3-turbo CPU, ~4.4x tiempo real), `trans\ogg_NN.txt` / `trans\mp4_NN.txt`
  - `jv\` copia compilada + `Probe.java`, `Probe2.java`

## VERSIÓN 4 (reescritura por legibilidad, ~02:00)
- El usuario sintió que algunas cosas estaban "explicadas de manera rara". Se reescribió el HTML en formato pregunta/respuesta corta con ejemplos (Ana canjea 100 millas; árbol 20/10/30/5/15/12/17 para el iterador; "av" en el Trie; tope 300; tokens 1-10 y 2-20). PDF = 5 páginas. Mismo artifact (versión 4).
- Lección: explicar en lenguaje llano + ejemplo concreto; evitar frases abstractas y exceso de identificadores en código.

## ESTADO FINAL (~01:35)
- Transcripción completa (ogg + mp4) leída; los mp4 repiten los ogg (misma reunión), sin contenido nuevo salvo precisiones. HTML en **versión 3** publicada (https://claude.ai/artifact/KhaSkULUUXDwBjN8s54DDe); como PDF sale en **4 páginas A4** (probado con Edge headless). Temporales (transcripciones, jv, pdf/png de prueba) BORRADOS: las rutas del scratchpad de abajo ya no existen. Si el usuario pide PDF: convertir `TP 2/defensa_tp2.html` con `msedge --headless=new --no-pdf-header-footer --print-to-pdf=...` (Edge en `C:\Program Files (x86)\Microsoft\Edge\Application\msedge.exe`; no hay pdftoppm para rasterizar).
- Punto 3 del HTML (interfaces plantilla): en la reunión alguien recordó que el profe dijo que esos archivos "no se tocan"; otro dudó si el iterador debía heredar `Iterador`. Sin resolver.

## Estado anterior (00:45)
- ogg 1–12 transcriptos y LEÍDOS. mp4 en curso (termina ~01:13). mp4 = misma reunión, mejor captación de voces (ej.: "ya está entregado, hay que dejarlo así" no salía en ogg). mp4 suman ~20 min más que ogg → leer sobre todo el final de mp4_03/04 y mp4_02..04 por contenido extra.
- **HTML v1 escrito y publicado**: archivo `TP 2/defensa_tp2.html`; artifact privado https://claude.ai/artifact/KhaSkULUUXDwBjN8s54DDe (para actualizar: publicar el mismo file_path). Falta: cruzar con mp4 y ajustar; el usuario pedirá PDF después (conversión local con Edge/Chrome headless; `@page` A4 ya incluido).
- Errores típicos de transcripción: ABB→"BB/bebé/ave", Trie→"TRAI/TRI/try", compareTo→"compertú", heap→"hip/chip", array→"arroyo/RAID", Handle→"Handel", Felipe→"Félix". Contrastar con el código.

## Código (verificado por lectura)
- Estructuras: `ABB<Usuario>` por id; `Trie<Promocion>` (hijos `ArrayList<Nodo>` de 256, índice = ASCII); `HeapHandle<ElementoRanking>` (max-heap sobre `ArrayList<Handle>`); `ABB<NodoHandle>` id→handle; `elMasAcumulador`/`elMasCanjeador` guardan **ids** (arrancan en -1, el primer usuario registrado los setea).
- ABB usado como diccionario: se busca con objeto "llave" mínimo (`new Usuario(id)`, `new NodoHandle(id,null)`); `compareTo` solo por id. `devolverUsuario`/`obtenerHandle` son auxiliares de legibilidad.
- `NodoHandle` repite el id (ya está dentro de `Handle.valor`): `Handle.valor` es privado → el ABB necesita su propia clave (inferencia; en la reunión lo llamaron "por ahí un error").
- `Handle` es clase interna de `HeapHandle`, constructor privado: solo el heap crea handles (encapsulamiento). Guarda `valor` y `pos`; `swap` actualiza `pos`. No implementa la interfaz `aed.tp2.Handle` de la plantilla ni `ABB_Iterador` implementa `Iterador` (grep: nadie las usa). En la reunión el autor del heap dijo que esa parte la consultó y que podía haber dudas.
- `HeapHandle.borrar(h)`: swap con el último, remove, y luego `siftUp(i)` **y** `siftDown(i)` (2·log n = O(log n)). `modificarPrioridad`: compara viejo/nuevo y sube o baja; igual → no mueve. `siftDown` elige el MAYOR de los dos hijos (el heap no ordena hermanos; en la reunión hubo confusión con "izquierdista"). Heap = árbol binario completo en array (padre (i-1)/2, hijos 2i+1, 2i+2), no es AVL.
- `ElementoRanking.compareTo`: Double.compare de millasDisponibles; desempate: menor id = "mayor" (`Integer.compare(otro.id, this.id)`) → orden total, ranking determinístico.
- `rankingMillas(n)`: k=min(n,tamaño); desencola k (quedan ordenados), guarda en ArrayList, y los **reencola** (handles NUEVOS) actualizando `NodoHandle.handle` en el ABB. Si no, handles viejos inválidos (test `rankingSeActualizaDespuesDeConsultarlo`).
- Millas disponibles duplicadas (en `Usuario` y en `ElementoRanking`): toda modificación debe llamar `modificarPrioridad` (referido, acumular, canjear, transferir origen y destino).
- Transferencia: destino NO suma millasTotales (aclaración TP2: no afecta categoría ni elMasAcumulador; en la reunión: "cambió respecto al TP1, por aclaración"); origen suma millasPerdidas (cuenta para elMasCanjeador). **Verificado**: destino con 0 totales recibe 100 → disponible 100 > totales (categoría Bronce) → el invariante "disponibles ≤ totales" de TP1 ya no vale con la regla de TP2. TP1 no dice explícitamente que el destino suma totales.
- Exclusividad: `tokenTransferidor` 0=sin transferencias, -1=no exclusivo, >0=id del par. `registrarTransferencia`: (1) ambos 0 → se emparejan; (2) ya son pareja → nada; (3) si no: `romperExclusividad` de ambos (setea -1 a las parejas anteriores) y token -1 en origen y destino. Afecta como mucho 4 usuarios (origen, destino y sus dos parejas anteriores) → O(log U), no hace falta while. `paresTransferidoresExclusivos`: ArrayList (amortizado) + un único `int[][]` final de tamaño exacto; agrega (id, token) solo si token>0 y `u.id > token` (evita duplicar (A,B)/(B,A)).
- `ABB_Iterador`: `actual` = nodo que se va a devolver; `siguiente()` devuelve su valor y avanza con `minimoMasGrandeQue`: si tiene hijo derecho → mínimo de la derecha; si no, sube mientras sea hijo derecho y después un padre más (puede dar null al final). Por llamada O(log n)/O(n); recorrer todo O(U) (cada arista ≤2 veces). El comentario del código (`U * O(1)`) es simplificación.
- `soyHijoDerecho` chequea `padre == null`; `soyHijoIzquierdo` no (solo se llama tras `soyRaiz` en `eliminar`; el iterador sube hasta la raíz con `soyHijoDerecho`). `AirMiles` nunca llama `ABB.eliminar`. `eliminar` con 2 hijos: copia el valor del sucesor (mínimo de la rama derecha) y elimina el sucesor.
- Trie: `insertar` ignora si la clave ya tiene valor (alternativa válida: sobrescribir); `eliminar` solo pone `valor=null` (no libera nodos); `crearArrayDeHijos` recorre 256 → constante, O(1); hijos se crean perezosamente al pasar por el nodo (no se crea el árbol completo); sin símbolo `$` (la palabra termina donde `valor != null`). Es la variante "array de punteros" de T11. Trie sin altura/cardinal: decisión consciente (la complejidad se cumple sin eso).
- Constructor: `NoPromo` (factor 1, tope -1 = sin tope) en el Trie; |"NoPromo"|=7 → O(1).
- `acumularMillas`: promo inexistente/eliminada → `NoPromo`. Con tope: si `ganadas <= tope && tope > 0` descuenta tope (y si llega a 0 elimina la promo); si no, y tope==-1 suma sin tocar tope.
- `elMasAcumulador` usa millas TOTALES (se actualiza en registrar-con-referidor y acumular); `elMasCanjeador` usa millas PERDIDAS (canjear y transferir-origen). Comparaciones estrictas (>): ante empate se queda el anterior.
- Categorías: `factorCategoria`/`nombreCategoria` en `Usuario` (copiado de TP1); el `return 0.0` / `"Platino"` final "va porque si no Java chilla".

## VERIFICADO ejecutando (Probe/Probe2; copia en scratchpad)
- Tope 300, factor 3: vuelo 50km (gana 150, tope queda 150) → saldo 150; vuelo 100km (gana 300 > 150) → **se ignora por completo** (saldo 150, ni siquiera con factor 1); vuelo 10km (gana 30) → 180; vuelo 40km (gana 120 = tope restante) → 300 y la promo se elimina; luego 100km sin promo → 400. Un único vuelo que ganaría 600 con tope 300 deja saldo 0.
- Es decisión consciente (reunión, ogg 11): "si esto no se cumpliese, el usuario no puede volar con esta promo" (spec TP1: "no se pase del tope al aplicar la promoción"). **No es bug**: es pregunta posible; responder con eso. CORRECCIÓN: yo había dicho antes que la promo "queda trabada": falso, solo se ignoran vuelos que excederían el tope restante.

## Complejidades (enunciado TP2)
crear O(1) · registrar(±ref) O(log U) prom / O(U) peor · acumular O(log U+|L|) prom / O(U+|L|) peor · canjear, transferir, consultar* O(log U) prom / O(U) peor · iniciar/finalizar promo O(|L|) · elMas* O(1) · ranking **O(N log U)** (peor caso, "salvo que se indique lo contrario") · pares exclusivos O(U).
- Punto flojo (lectura): `rankingMillas` busca N handles en el ABB (O(log U) prom, O(U) peor) → peor caso O(N·U); la justificación.pdf no lo menciona. Preparar respuesta (ABB sin balancear solo degrada con ids ordenados; ranking cuesta O(N log U) en promedio).

## Consejos que se dieron ustedes en la reunión (para el HTML)
- Estudiar las teóricas (ABB, heap, trie): quien explica la estructura explica el código. Heap: todo salió de la clase de heap (fórmulas padre/hijos, siftUp/siftDown); la teórica ayudó más que el lab; no había ejemplo con handles.
- Si preguntan "¿por qué no X / por qué ese nombre?": reconocer que podría haber sido de otra forma, explicar la decisión; no lo toman como error (ej.: `puedoIrALaIzquierda` ≈ "tengo hijo izquierdo", falta "tengo un solo hijo" en eliminar).
- Tener la especificación TP1 a mano: el código sigue esa spec.
- Pedir una "explicación estándar" para casos difíciles (eliminar con 2 hijos, iterador, romper exclusividad) para que los 4 digan lo mismo.
- Posible pregunta general: "cuéntenme cómo hicieron ABB, heap, handle" (no función por función).
- Posibles preguntas anotadas: por qué constructor del Trie es O(1) con un for de 256; por qué ArrayList y luego `int[][]` en pares exclusivos (si redimensionás un array común a cada paso no da la complejidad); por qué Trie sin altura/cardinal; por qué insertar ignora si ya existe; por qué `eliminar` solo pone valor null; por qué se busca con `new Usuario(id)`; qué es `compareTo` (relación de orden, no `equals`); por qué `siftUp` y `siftDown` en borrar; por qué 4 usuarios máximo al romper exclusividad; por qué NoPromo es O(1); por qué el destino de una transferencia no suma totales; por qué se quitaron los chequeos de null (cobertura mostró que nunca se ejecutan; los cubre el requiere); ABB: `i < cardinal()` es redundante (admitido).
- Decisión de grupo: usaron la implementación del primero que terminó; se ayudaron entre los cuatro. Re-entrega posible (límite dom 22/11).

## Pendiente
- Esperar mp4 (~01:15), leerlos (buscar contenido extra y cruzar con ogg).
- Mirar T10/T11 si hace falta citar (heap completo, trie).
- Escribir HTML (~4 págs), publicar artifact privado, borrar temporales (scratchpad) al final.
