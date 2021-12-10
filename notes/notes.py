from pathlib import Path
from textwrap import dedent

from pygments import highlight as pygments_highlight
from pygments.lexers import get_lexer_for_filename
from pygments.formatters import HtmlFormatter

from IPython.display import HTML

CURRENT_BRANCH = 'master'
BASE_DIR = '../src/main/java/'
BASE_URL = f'https://github.com/prog2-unimi/notes/blob/{CURRENT_BRANCH}/src/'

def find(fragment, lines):
    oc = 0
    first = -1
    for n, line in enumerate(lines, 1):
        if fragment in line: first = n
        if first != -1:
            oc += line.count('{') - line.count('}')
            if oc == 0: return first, 1 + n
    return None, None

def show(path, linenos = False, first = None, last = None, highlight = None, fragment = None):
    code = (Path(BASE_DIR) / path).read_text()
    lines = code.splitlines()
    if fragment: first, last = find(fragment, lines)
    first = 0 if first is None else first - 1
    last = len(lines) if last is None else last - 1
    url = BASE_URL + path
    if first > 0 or last < len(lines):
        url += '#L{}-L{}'.format(first + 1, last)
    code = '\n'.join(code.splitlines()[first:last])
    if isinstance(highlight, list):
      highlight = [l - first for l in highlight]
    elif isinstance(highlight, str):
      hf, hl = find(highlight, lines)
      highlight = list(range(hf - first, hl - first))
    lexer = get_lexer_for_filename(path, stripall = False)
    formatter = HtmlFormatter(
        linenos = 'inline' if linenos else None,
        linenostart = 1 if first is None else 1 + first,
        cssclass = 'output_html',
        nobackground = False,
        hl_lines = highlight if highlight else []
    )
    return HTML('<style>{}</style>{}<p><a href="{}" target="_blank">[sorgente]</a>'.format(
        formatter.get_style_defs('.output_html'),
        pygments_highlight(dedent(code), lexer, formatter),
        url
    ))