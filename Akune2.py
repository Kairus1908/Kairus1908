import tkinter as tk
from tkinter import ttk
import keyboard
import time
import pygetwindow as gw
from PIL import ImageTk, Image


# Variáveis globais
teclas = []
intervalo = 0.1
repeticoes = 10
entry_teclas = None
entry_intervalo = None
entry_repeticoes = None
combobox_janelas = None


def repetir_teclas():
    global combobox_janelas
    print(f"Repetindo as teclas '{', '.join(teclas)}' {repeticoes} vezes com um intervalo de {intervalo} segundos.")
    time.sleep(2)  # Aguarda 2 segundos antes de começar a repetição

    # Obtém o título da janela selecionada na combobox
    titulo_janela = combobox_janelas.get()

    # Verifica se o título da janela está na lista de janelas abertas
    janelas = gw.getWindowsWithTitle(titulo_janela)
    if len(janelas) > 0:
        # Obtém a janela específica com base no título
        janela = janelas[0]
        janela.activate()  # Ativa a janela

        for _ in range(repeticoes):
            for tecla in teclas:
                keyboard.press(tecla)
                keyboard.release(tecla)
                time.sleep(intervalo)

        print("Repetição concluída.")
    else:
        print("Erro: Janela não encontrada.")


def definir_teclas():
    global teclas, entry_teclas
    teclas = entry_teclas.get().split()
    print(f"As teclas definidas são: {', '.join(teclas)}.")


def definir_intervalo():
    global intervalo, entry_intervalo
    intervalo = float(entry_intervalo.get())
    print(f"O intervalo foi definido como {intervalo} segundos.")


def definir_repeticoes():
    global repeticoes, entry_repeticoes
    repeticoes = int(entry_repeticoes.get())
    print(f"O número de repetições foi definido como {repeticoes}.")


def cancelar_repeticao():
    print("Repetição de teclas cancelada.")
    keyboard.unhook_all()


# Função para formatar o nome das janelas
def formatar_nome_janela(nome_janela):
    if len(nome_janela) > 20:
        return nome_janela[:20] + "..."
    else:
        return nome_janela


def main():
    global entry_teclas, entry_intervalo, entry_repeticoes, combobox_janelas
    # Criação da janela principal
    window = tk.Tk()
    window.title("Akune's Macro")

    # Carregar a imagem de plano de fundo
    imagem = Image.open("C:\\Users\\kauap\\Desktop\\Python\\Akune.png")
    imagem = imagem.resize((700, 500), Image.ANTIALIAS)  # Redimensiona a imagem para o tamanho desejado
    imagem = ImageTk.PhotoImage(imagem)

    # Criar o widget Label para exibir a imagem de plano de fundo
    label_imagem = tk.Label(window, image=imagem)
    label_imagem.grid(row=0, column=0, columnspan=3, rowspan=8, sticky="nsew")

    # Label e Entry para definir as teclas
    label_teclas = tk.Label(window, text="Digite as teclas a serem repetidas (separadas por espaço):")
    label_teclas.grid(row=0, column=3, padx=10, pady=10)

    entry_teclas = tk.Entry(window)
    entry_teclas.grid(row=0, column=4, padx=10, pady=10)

    botao_definir_teclas = tk.Button(window, text="Definir Teclas", command=definir_teclas)
    botao_definir_teclas.grid(row=0, column=5, padx=10, pady=10)

    # Label e Entry para definir o intervalo
    label_intervalo = tk.Label(window, text="Intervalo entre as teclas (em segundos):")
    label_intervalo.grid(row=1, column=3, padx=10, pady=10)

    entry_intervalo = tk.Entry(window)
    entry_intervalo.grid(row=1, column=4, padx=10, pady=10)

    botao_definir_intervalo = tk.Button(window, text="Definir Intervalo", command=definir_intervalo)
    botao_definir_intervalo.grid(row=1, column=5, padx=10, pady=10)

    # Label e Entry para definir as repetições
    label_repeticoes = tk.Label(window, text="Número de repetições:")
    label_repeticoes.grid(row=2, column=3, padx=10, pady=10)

    entry_repeticoes = tk.Entry(window)
    entry_repeticoes.grid(row=2, column=4, padx=10, pady=10)

    botao_definir_repeticoes = tk.Button(window, text="Definir Repetições", command=definir_repeticoes)
    botao_definir_repeticoes.grid(row=2, column=5, padx=10, pady=10)

    # Combobox para selecionar a janela
    label_janelas = tk.Label(window, text="Selecione uma janela:")
    label_janelas.grid(row=3, column=3, padx=10, pady=10)

    combobox_janelas = ttk.Combobox(window, values=[formatar_nome_janela(janela.title) for janela in gw.getAllWindows()])
    combobox_janelas.grid(row=3, column=4, padx=10, pady=10)

    # Botão para iniciar a repetição de teclas
    botao_repetir_teclas = tk.Button(window, text="Repetir Teclas", command=repetir_teclas)
    botao_repetir_teclas.grid(row=4, column=3, padx=10, pady=10)

    # Botão para cancelar a repetição de teclas
    botao_cancelar_repeticao = tk.Button(window, text="Cancelar Repetição", command=cancelar_repeticao)
    botao_cancelar_repeticao.grid(row=4, column=4, padx=10, pady=10)

    # Iniciar a execução da janela
    window.mainloop()


if __name__ == "__main__":
    main()