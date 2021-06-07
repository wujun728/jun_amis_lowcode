(function () {
  initHashLevelRedirects()
  initMobileMenu()
  initVideoModal()
  initNewNavLinks()
  if (PAGE_TYPE) {
    initVersionSelect()
    initApiSpecLinks()
    initSubHeaders()
    initLocationHashFuzzyMatching()
  }

  // Most redirects should be specified in Hexo's
  // _config.yml. However, it can't handle hash-level
  // redirects, such as:
  //
  // /foo#hello -> /bar#hello
  //
  // For these cases where a section on one page has
  // moved to a perhaps differently-named section on
  // another page, we need this.
  function initHashLevelRedirects() {
    checkForHashRedirect(/list\.html$/, {
      key: '/v2/guide/list.html#缁存姢鐘舵€�'
    })
    checkForHashRedirect(/components\.html$/, {
      '浠€涔堟槸缁勪欢锛�': 'http://www.active4j.com/v2/guide/components.html',
      '浣跨敤缁勪欢': 'http://www.active4j.com/v2/guide/components-registration.html',
      '鍏ㄥ眬娉ㄥ唽': '/v2/guide/components-registration.html#鍏ㄥ眬娉ㄥ唽',
      '灞€閮ㄦ敞鍐�': '/v2/guide/components-registration.html#灞€閮ㄦ敞鍐�',
      'DOM-妯℃澘瑙ｆ瀽娉ㄦ剰浜嬮」': '/v2/guide/components.html#瑙ｆ瀽-DOM-妯℃澘鏃剁殑娉ㄦ剰浜嬮」',
      'DOM-妯℃澘瑙ｆ瀽璇存槑': '/v2/guide/components.html#瑙ｆ瀽-DOM-妯℃澘鏃剁殑娉ㄦ剰浜嬮」',
      'data-蹇呴』鏄嚱鏁�': '/v2/guide/components.html#data-蹇呴』鏄竴涓嚱鏁�',
      '缁勪欢缁勫悎': '/v2/guide/components.html#缁勪欢鐨勭粍缁�',
      'Prop': '/v2/guide/components.html#閫氳繃-Prop-鍚戝瓙缁勪欢浼犻€掓暟鎹�',
      'Props': '/v2/guide/components.html#閫氳繃-Prop-鍚戝瓙缁勪欢浼犻€掓暟鎹�',
      '浣跨敤-Prop-浼犻€掓暟鎹�': '/v2/guide/components.html#閫氳繃-Prop-鍚戝瓙缁勪欢浼犻€掓暟鎹�',
      'camelCase-vs-kebab-case': '/v2/guide/components-props.html#Prop-鐨勫ぇ灏忓啓-camelCase-vs-kebab-case',
      '鍔ㄦ€�-Prop': '/v2/guide/components-props.html#闈欐€佺殑鍜屽姩鎬佺殑-Prop',
      '瀛楅潰閲忚娉�-vs-鍔ㄦ€佽娉�': '/v2/guide/components-props.html#闈欐€佺殑鍜屽姩鎬佺殑-Prop',
      '鍗曞悜鏁版嵁娴�': '/v2/guide/components-props.html#鍗曞悜鏁版嵁娴�',
      'Prop-楠岃瘉': '/v2/guide/components-props.html#Prop-楠岃瘉',
      '闈�-Prop-鐗规€�': '/v2/guide/components-props.html#闈�-Prop-鐨勭壒鎬�',
      '鏇挎崲-鍚堝苟鐜版湁鐨勭壒鎬�': '/v2/guide/components-props.html#鏇挎崲-鍚堝苟宸叉湁鐨勭壒鎬�',
      '鑷畾涔変簨浠�': '/v2/guide/components.html#鐩戝惉瀛愮粍浠朵簨浠�',
      '浣跨敤-v-on-缁戝畾鑷畾涔変簨浠�': '/v2/guide/components.html#鐩戝惉瀛愮粍浠朵簨浠�',
      '缁欑粍浠剁粦瀹氬師鐢熶簨浠�': '/v2/guide/components-custom-events.html#灏嗗師鐢熶簨浠剁粦瀹氬埌缁勪欢',
      'sync-淇グ绗�': '/v2/guide/components-custom-events.html#sync-淇グ绗�',
      '浣跨敤鑷畾涔変簨浠剁殑琛ㄥ崟杈撳叆缁勪欢': '/v2/guide/components-custom-events.html#灏嗗師鐢熶簨浠剁粦瀹氬埌缁勪欢',
      '鑷畾涔夌粍浠剁殑-v-model': '/v2/guide/components-custom-events.html#鑷畾涔夌粍浠剁殑-v-model',
      '鍦ㄧ粍浠朵笂浣跨敤-v-model': '/v2/guide/components-custom-events.html#鑷畾涔夌粍浠剁殑-v-model',
      '闈炵埗瀛愮粍浠剁殑閫氫俊': 'http://www.active4j.com/v2/guide/state-management.html',
      '浣跨敤鎻掓Ы鍒嗗彂鍐呭': '/v2/guide/components.html#閫氳繃鎻掓Ы鍒嗗彂鍐呭',
      '缂栬瘧浣滅敤鍩�': '/v2/guide/components-slots.html#缂栬瘧浣滅敤鍩�',
      '鍗曚釜鎻掓Ы': '/v2/guide/components-slots.html#鎻掓Ы鍐呭',
      '鍏峰悕鎻掓Ы': '/v2/guide/components-slots.html#鍏峰悕鎻掓Ы',
      '浣滅敤鍩熸彃妲�': '/v2/guide/components-slots.html#浣滅敤鍩熸彃妲�',
      '鍔ㄦ€佺粍浠�': '/v2/guide/components.html#鍔ㄦ€佺粍浠�',
      'keep-alive': '/v2/guide/components-dynamic-async.html#鍦ㄥ姩鎬佺粍浠朵笂浣跨敤-keep-alive',
      '鏉傞」': 'http://www.active4j.com/v2/guide/components-edge-cases.html',
      '缂栧啓鍙鐢ㄧ粍浠�': '/v2/guide/components.html#缁勪欢鐨勭粍缁�',
      '瀛愮粍浠跺紩鐢�': '/v2/guide/components-edge-cases.html#璁块棶瀛愮粍浠跺疄渚嬫垨瀛愬厓绱�',
      '瀛愮粍浠剁储寮�': '/v2/guide/components-edge-cases.html#璁块棶瀛愮粍浠跺疄渚嬫垨瀛愬厓绱�',
      '寮傛缁勪欢': '/v2/guide/components-dynamic-async.html#寮傛缁勪欢',
      '楂樼骇寮傛缁勪欢': '/v2/guide/components-dynamic-async.html#澶勭悊鍔犺浇鐘舵€�',
      '缁勪欢鍛藉悕绾﹀畾': '/v2/guide/components-registration.html#缁勪欢鍚�',
      '閫掑綊缁勪欢': '/v2/guide/components-edge-cases.html#閫掑綊缁勪欢',
      '缁勪欢闂寸殑寰幆寮曠敤': '/v2/guide/components-edge-cases.html#缁勪欢涔嬮棿鐨勫惊鐜紩鐢�',
      '鍐呰仈妯℃澘': '/v2/guide/components-edge-cases.html#鍐呰仈妯℃澘',
      'X-Templates': '/v2/guide/components-edge-cases.html#X-Templates',
      '瀵逛綆寮€閿€鐨勯潤鎬佺粍浠朵娇鐢�-v-once': '/v2/guide/components-edge-cases.html#閫氳繃-v-once-鍒涘缓浣庡紑閿€鐨勯潤鎬佺粍浠�'
    })
    function checkForHashRedirect(pageRegex, redirects) {
      // Abort if the current page doesn't match the page regex
      if (!pageRegex.test(window.location.pathname)) return

      var redirectPath = redirects[decodeURIComponent(window.location.hash.slice(1))]
      if (redirectPath) {
        window.location.href = window.location.origin + redirectPath
      }
    }
  }

  function initApiSpecLinks () {
    var apiContent = document.querySelector('http://www.active4j.com/static/js/.content.api')
    if (apiContent) {
      var apiTitles = [].slice.call(apiContent.querySelectorAll('h3'))
      apiTitles.forEach(function (titleNode) {
        var methodMatch = titleNode.textContent.match(/^([^(]+)\(/)
        if (methodMatch) {
          var idWithoutArguments = slugize(methodMatch[1])
          titleNode.setAttribute('id', idWithoutArguments)
          titleNode.querySelector('a').setAttribute('href', '#' + idWithoutArguments)
        }

        var ulNode = titleNode.parentNode.nextSibling
        if (ulNode.tagName !== 'UL') {
          ulNode = ulNode.nextSibling
          if (!ulNode) return
        }
        if (ulNode.tagName === 'UL') {
          var specNode = document.createElement('li')
          var specLink = createSourceSearchPath(titleNode.textContent)
          specNode.innerHTML = '<a href="' + specLink + '" target="_blank" rel="noopener">婧愪唬鐮�</a>'
          ulNode.appendChild(specNode)
        }
      })
    }

    function createSourceSearchPath (query) {
      query = query
        .replace(/\([^\)]*?\)/g, '')
        .replace(/(Vue\.)(\w+)/g, '$1$2" OR "$2')
        .replace(/vm\./g, 'Vue.prototype.')
      return 'https://github.com/search?utf8=%E2%9C%93&q=repo%3Avuejs%2Fvue+extension%3Ajs+' + encodeURIComponent('"' + query + '"') + '&type=Code'
    }
  }

  function parseRawHash (hash) {
    // Remove leading hash
    if (hash.charAt(0) === '#') {
      hash = hash.substr(1)
    }

    // Escape characters
    try {
      hash = decodeURIComponent(hash)
    } catch (e) {}
    return CSS.escape(hash)
  }

  function initLocationHashFuzzyMatching () {
    var rawHash = window.location.hash
    if (!rawHash) return
    var hash = parseRawHash(rawHash)
    var hashTarget = document.getElementById(hash)
    if (!hashTarget) {
      var normalizedHash = normalizeHash(hash)
      var edgeCases = {
        'vue-set-target-key-value': 'vue-set'
      }
      if (edgeCases.hasOwnProperty(normalizedHash)) {
        normalizedHash = edgeCases[normalizedHash];
      }
      var possibleHashes = [].slice.call(document.querySelectorAll('[id]'))
        .map(function (el) { return el.id })
      possibleHashes.sort(function (hashA, hashB) {
        var distanceA = levenshteinDistance(normalizedHash, normalizeHash(hashA))
        var distanceB = levenshteinDistance(normalizedHash, normalizeHash(hashB))
        if (distanceA < distanceB) return -1
        if (distanceA > distanceB) return 1
        return 0
      })
      window.location.hash = '#' + possibleHashes[0]
    }

    function normalizeHash (rawHash) {
      return rawHash
        .toLowerCase()
        .replace(/\-(?:deprecated|removed|replaced|changed|obsolete)$/, '')
    }

    function levenshteinDistance (a, b) {
      var m = []
      if (!(a && b)) return (b || a).length
      for (var i = 0; i <= b.length; m[i] = [i++]) {}
      for (var j = 0; j <= a.length; m[0][j] = j++) {}
      for (var i = 1; i <= b.length; i++) {
        for (var j = 1; j <= a.length; j++) {
          m[i][j] = b.charAt(i - 1) === a.charAt(j - 1)
            ? m[i - 1][j - 1]
            : m[i][j] = Math.min(
              m[i - 1][j - 1] + 1,
              Math.min(m[i][j - 1] + 1, m[i - 1][j] + 1))
        }
      }
      return m[b.length][a.length]
    }
  }

  /**
   * Initializes a list of links to mark as "updated" by adding a red dot next to them
   */

  function initNewNavLinks() {
    var linkExpirePeriod = 60 * 24 * 3600 * 1000 // 2 months
    var links = [
      {
        title: 'Resources',
        updatedOn: new Date("Mon Sep 9 2019")
      }
    ]
    var today = new Date().getTime()
    var updatedLinks = links
      .filter(function (link) {
        return link.updatedOn.getTime() + linkExpirePeriod > today
      })
      .map(function (link) {
        return link.title
      })

    var navLinks = document.querySelectorAll('#nav a.nav-link')
    var newLinks = []
    navLinks.forEach(function (link) {
      if (updatedLinks.indexOf(link.textContent) !== -1) {
        newLinks.push(link)
      }
    })
    newLinks.forEach(function (link) {
      var classes = link.classList
      var linkKey = `visited-${link.textContent}`
      if (localStorage.getItem(linkKey) || classes.contains('current')) {
        classes.remove('updated-link')
        localStorage.setItem(linkKey, 'true')
      } else {
        classes.add('new')
      }
    })
  }

  /**
   * Mobile burger menu button and gesture for toggling sidebar
   */

  function initMobileMenu () {
    var mobileBar = document.getElementById('mobile-bar')
    var sidebar = document.querySelector('.sidebar')
    var menuButton = mobileBar.querySelector('.menu-button')

    menuButton.addEventListener('click', function () {
      sidebar.classList.toggle('open')
    })

    document.body.addEventListener('click', function (e) {
      if (e.target !== menuButton && !sidebar.contains(e.target)) {
        sidebar.classList.remove('open')
      }
    })

    // Toggle sidebar on swipe
    var start = {}, end = {}

    document.body.addEventListener('touchstart', function (e) {
      start.x = e.changedTouches[0].clientX
      start.y = e.changedTouches[0].clientY
    })

    document.body.addEventListener('touchend', function (e) {
      end.y = e.changedTouches[0].clientY
      end.x = e.changedTouches[0].clientX

      var xDiff = end.x - start.x
      var yDiff = end.y - start.y

      if (Math.abs(xDiff) > Math.abs(yDiff)) {
        if (xDiff > 0 && start.x <= 80) sidebar.classList.add('open')
        else sidebar.classList.remove('open')
      }
    })
  }

  /**
  * Modal Video Player
  */
  function initVideoModal () {
    var modalButton = document.getElementById('modal-player')
    var videoModal = document.getElementById('video-modal')

    if (!modalButton || !videoModal) {
      return
    }

    var videoWrapper = videoModal.querySelector('.video-space')
    var overlay = document.createElement('div')
        overlay.className = 'overlay'
    var isOpen = false

    modalButton.addEventListener('click', function(event) {
      event.stopPropagation()
      videoModal.classList.toggle('open')
      document.body.classList.toggle('stop-scroll')
      document.body.appendChild(overlay)
      videoWrapper.innerHTML = '<iframe style="height: 100%; left: 0; position: absolute; top: 0; width: 100%;" src="//player.youku.com/embed/XMzMwMTYyODMyNA==?autoplay=true&client_id=37ae6144009e277d" frameborder="0" allowfullscreen></iframe>'
      isOpen = true
    })

    document.body.addEventListener('click', function(e) {
      if (isOpen && e.target !== modalButton && !videoModal.contains(e.target)) {
        videoModal.classList.remove('open')
        document.body.classList.remove('stop-scroll')
        document.body.removeChild(overlay)
        videoWrapper.innerHTML = ''
        isOpen = false
      }
    })
  }

  /**
   * Doc version select
   */

  function initVersionSelect () {
    // version select
    var versionSelect = document.querySelector('.version-select')
    versionSelect && versionSelect.addEventListener('change', function (e) {
      var version = e.target.value
      var section = window.location.pathname.match(/\/v\d\/(\w+?)\//)[1]
      if (version === 'SELF') return
      window.location.assign(
        'https://' +
        version +
        (version && '.') +
        'vuejs.org/' + section + '/'
      )
    })
  }

  /**
   * Sub headers in sidebar
   */

  function initSubHeaders () {
    var each = [].forEach
    var main = document.getElementById('main')
    var header = document.getElementById('header')
    var sidebar = document.querySelector('.sidebar')
    var content = document.querySelector('.content')

    // build sidebar
    var currentPageAnchor = sidebar.querySelector('.sidebar-link.current')
    var contentClasses = document.querySelector('.content').classList
    var isAPIOrStyleGuide = (
      contentClasses.contains('api') ||
      contentClasses.contains('style-guide')
    )
    if (currentPageAnchor || isAPIOrStyleGuide) {
      var allHeaders = []
      var sectionContainer
      if (isAPIOrStyleGuide) {
        sectionContainer = document.querySelector('.menu-root')
      } else {
        sectionContainer = document.createElement('ul')
        sectionContainer.className = 'menu-sub'
        currentPageAnchor.parentNode.appendChild(sectionContainer)
      }
      var headers = content.querySelectorAll('h2')
      if (headers.length) {
        each.call(headers, function (h) {
          var listItem = makeLink(h)
          sectionContainer.appendChild(listItem)
          var h3s = collectH3s(h)
          allHeaders.push(h)
          allHeaders.push.apply(allHeaders, h3s)
          if (h3s.length) {
            listItem.appendChild(makeSubLinks(h3s, isAPIOrStyleGuide))
          }
        })
      } else {
        headers = content.querySelectorAll('h3')
        each.call(headers, function (h) {
          sectionContainer.appendChild(makeLink(h))
          allHeaders.push(h)
        })
      }

      var animating = false
      sectionContainer.addEventListener('click', function (e) {

        // Not prevent hashchange for smooth-scroll
        // e.preventDefault()

        if (e.target.classList.contains('section-link')) {
          sidebar.classList.remove('open')
          setActive(e.target)
          animating = true
          setTimeout(function () {
            animating = false
          }, 400)
        }
      }, true)

      // make links clickable
      allHeaders
        .filter(function(el) {
          if (!el.querySelector('a')) {
            return false
          }
          var demos = [].slice.call(document.querySelectorAll('demo'))
          return !demos.some(function(demoEl) {
            return demoEl.contains(el)
          })
        })
        .forEach(makeHeaderClickable)

      new SmoothScroll('a[href*="#"]', {
        speed: 400,
        speedAsDuration: true,
        offset: function (anchor, toggle) {
          let dataTypeAttr = anchor.attributes['data-type']
          if(dataTypeAttr && dataTypeAttr.nodeValue === 'theme-product-title') {
            return 300
          }
          return 0
        }
      })
    }

    var hoveredOverSidebar = false
    sidebar.addEventListener('mouseover', function () {
      hoveredOverSidebar = true
    })
    sidebar.addEventListener('mouseleave', function () {
      hoveredOverSidebar = false
    })

    // listen for scroll event to do positioning & highlights
    window.addEventListener('scroll', updateSidebar)
    window.addEventListener('resize', updateSidebar)

    function updateSidebar () {
      var doc = document.documentElement
      var top = doc && doc.scrollTop || document.body.scrollTop
      if (animating || !allHeaders) return
      var last
      for (var i = 0; i < allHeaders.length; i++) {
        var link = allHeaders[i]
        if (link.offsetTop > top) {
          if (!last) last = link
          break
        } else {
          last = link
        }
      }
      if (last)
        setActive(last.id, !hoveredOverSidebar)
    }

    function makeLink (h) {
      var link = document.createElement('li')
      window.arst = h
      var text = [].slice.call(h.childNodes).map(function (node) {
        if (node.nodeType === Node.TEXT_NODE) {
          return node.nodeValue
        } else if (['CODE', 'SPAN'].indexOf(node.tagName) !== -1) {
          return node.textContent
        } else {
          return ''
        }
      }).join('').replace(/\(.*\)$/, '')
      link.innerHTML =
        '<a class="section-link" data-scroll href="#' + h.id + '">' +
          htmlEscape(text) +
        '</a>'
      return link
    }

    function htmlEscape (text) {
      return text
        .replace(/&/g, '&amp;')
        .replace(/"/g, '&quot;')
        .replace(/'/g, '&#39;')
        .replace(/</g, '&lt;')
        .replace(/>/g, '&gt;')
    }

    function collectH3s (h) {
      var h3s = []
      var next = h.nextSibling
      while (next && next.tagName !== 'H2') {
        if (next.tagName === 'H3') {
          h3s.push(next)
        }
        next = next.nextSibling
      }
      return h3s
    }

    function makeSubLinks (h3s, small) {
      var container = document.createElement('ul')
      if (small) {
        container.className = 'menu-sub'
      }
      h3s.forEach(function (h) {
        container.appendChild(makeLink(h))
      })
      return container
    }

    function setActive (id, shouldScrollIntoView) {
      var previousActive = sidebar.querySelector('.section-link.active')
      var currentActive = typeof id === 'string'
        ? sidebar.querySelector('.section-link[href="#' + id + '"]')
        : id
      if (currentActive !== previousActive) {
        if (previousActive) previousActive.classList.remove('active')
        currentActive.classList.add('active')
        if (shouldScrollIntoView) {
          var currentPageOffset = currentPageAnchor
            ? currentPageAnchor.offsetTop - 8
            : 0
          var currentActiveOffset = currentActive.offsetTop + currentActive.parentNode.clientHeight
          var sidebarHeight = sidebar.clientHeight
          var currentActiveIsInView = (
            currentActive.offsetTop >= sidebar.scrollTop &&
            currentActiveOffset <= sidebar.scrollTop + sidebarHeight
          )
          var linkNotFurtherThanSidebarHeight = currentActiveOffset - currentPageOffset < sidebarHeight
          var newScrollTop = currentActiveIsInView
            ? sidebar.scrollTop
            : linkNotFurtherThanSidebarHeight
              ? currentPageOffset
              : currentActiveOffset - sidebarHeight
          sidebar.scrollTop = newScrollTop
        }
      }
    }

    function makeHeaderClickable (header) {
      var link = header.querySelector('a')
      link.setAttribute('data-scroll', '')

      // transform DOM structure from
      // `<h2><a></a>Header</a>` to <h2><a>Header</a></h2>`
      // to make the header clickable
      var nodes = Array.prototype.slice.call(header.childNodes)
      for (var i = 0; i < nodes.length; i++) {
        var node = nodes[i]
        if (node !== link) {
          link.appendChild(node)
        }
      }
    }
  }

  // Stolen from: https://github.com/hexojs/hexo-util/blob/master/lib/escape_regexp.js
  function escapeRegExp(str) {
    if (typeof str !== 'string') throw new TypeError('str must be a string!');

    // http://stackoverflow.com/a/6969486
    return str.replace(/[\-\[\]\/\{\}\(\)\*\+\?\.\\\^\$\|]/g, '\\$&');
  }

  // Stolen from: https://github.com/hexojs/hexo-util/blob/master/lib/slugize.js
  function slugize(str, options) {
    if (typeof str !== 'string') throw new TypeError('str must be a string!')
    options = options || {}

    var rControl = /[\u0000-\u001f]/g
    var rSpecial = /[\s~`!@#\$%\^&\*\(\)\-_\+=\[\]\{\}\|\\;:"'<>,\.\?\/]+/g
    var separator = options.separator || '-'
    var escapedSep = escapeRegExp(separator)

    var result = str
      // Remove control characters
      .replace(rControl, '')
      // Replace special characters
      .replace(rSpecial, separator)
      // Remove continuous separators
      .replace(new RegExp(escapedSep + '{2,}', 'g'), separator)
      // Remove prefixing and trailing separators
      .replace(new RegExp('^' + escapedSep + '+|' + escapedSep + '+$', 'g'), '')

    switch (options.transform) {
      case 1:
        return result.toLowerCase()
      case 2:
        return result.toUpperCase()
      default:
        return result
    }
  }
})()